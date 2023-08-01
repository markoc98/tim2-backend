package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.LimitingIngredient;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.models.entities.RecipeIngredient;
import com.praksa.KitchenBackEnd.repositories.IngredientRepository;
import com.praksa.KitchenBackEnd.repositories.LimitingIngredientRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeIngredientRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private LimitingIngredientRepository limIngredientRepo;
	
	@Autowired
	private RecipeIngredientRepository recipeIngreRepo;

	@Autowired 
	private CookService cookService;
	
	//IZLVACENJE ALERGENA
	private Set<LimitingFactor> extractLF(Recipe recipe) {
		
		List<Ingredient> ingredients = recipe.
				getIngredients().stream().map(e -> 
				e.getIngredientId()).toList();
		
		Set<LimitingFactor> limits = new HashSet<>();
		for(Ingredient ing : ingredients) { 
			for(LimitingIngredient limIng : ing.getLimitingFactors()) {
				limits.add(limIng.getLimitingFactor());
			}
		}
	
		return limits;
	}
	
	//IZVLACENJE SASTOJAKA
	private List<Ingredient> extractIng(Recipe recipe) {

		List<Ingredient> ingredients = recipe.
				getIngredients().stream().map(e -> 
				e.getIngredientId()).toList();
		
		return ingredients;
	}
	
	//RACUNANJE HRANLJIVOSTI SASTOJAKA
	private Map<String, Float> calculateNutrition(Recipe recipe) {
		List<Ingredient> ingredients = new ArrayList<>(extractIng(recipe));
		Integer amount = 0;
		
		for (RecipeIngredient recIng : recipe.getIngredients()) {
			if(ingredients.contains(recIng.getIngredientId())) {
					amount += recIng.getAmount();
				}
			}
		
		Map<String, Float> nutrition = new HashMap<>();
		nutrition.put("proteins", 0.00f);
		nutrition.put("carbs", 0.00f);
		nutrition.put("fats", 0.00f);
		nutrition.put("saturatedFats", 0.00f); 
		nutrition.put("sugars", 0.00f);
		nutrition.put("calories", 0.00f);
		
		for (Ingredient ingredient : ingredients) {
			for (Map.Entry<String, Float> entry : nutrition.entrySet()) {
				if(entry.getKey() == "proteins") {
				entry.setValue(entry.getValue() + ingredient.getProteins());
				} else if(entry.getKey() == "carbs") {
				entry.setValue(entry.getValue() + ingredient.getCarbs());
				} else if(entry.getKey() == "fats") {
				entry.setValue(entry.getValue() + ingredient.getFats());
				}else if(entry.getKey() == "saturatedFats") {
				entry.setValue(entry.getValue() + ingredient.getSaturatedFats());
				} else if(entry.getKey() == "sugars") {
				entry.setValue(entry.getValue() + ingredient.getSugars());
				} else if(entry.getKey() == "calories") {
				entry.setValue(entry.getValue() + ingredient.getCalories());
				}
			}
		}
			for (Map.Entry<String, Float> entry : nutrition.entrySet()) {
				entry.setValue(entry.getValue() * amount/100);
			}
		
		return nutrition;
	}
	

	
	@Override
	public RecipeDTO createRecipe(RecipeDTO newRecipe, Long cookId) {
		Recipe recipe = new Recipe();
		Cook cook = cookService.getCook(cookId);
		Ingredient ingredient = new Ingredient();
		recipe.setAmount(newRecipe.getAmount());
		recipe.setCook(cook);
		recipe.setSteps(newRecipe.getSteps());
		recipe.setTimeToPrepare(newRecipe.getTimeToPrepare());
		recipe.setTitle(newRecipe.getTitle());
		recipe.setDescription(newRecipe.getDescription());
		recipe.setIngredients(newRecipe.getIngredients());
		return newRecipe;
	}
	
	
	@Override
	public Recipe addRecipeToUser(Long userId, Long recipeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe getRecipe(Long id) {
		return recipeRepository.findById(id).get();
	}

	@Override
	public Recipe deleteRecipe(Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
		recipeRepository.delete(recipe);
		return recipe;
	}

	@Override
	public Recipe updateRecipe(RecipeDTO updatedRecipe, Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
		if(updatedRecipe.getAmount() != null) {
			recipe.setAmount(updatedRecipe.getAmount());			
		}
		if(updatedRecipe.getSteps() != null && !updatedRecipe.getSteps().equals(recipe.getSteps())) {
			recipe.setSteps(updatedRecipe.getSteps());			
		}
		if(updatedRecipe.getTimeToPrepare() != null && updatedRecipe.getTimeToPrepare() != recipe.getTimeToPrepare()) {
			recipe.setTimeToPrepare(updatedRecipe.getTimeToPrepare());			
		}
		if(updatedRecipe.getTitle() != null && !updatedRecipe.getTitle().equals(recipe.getTitle())) {
			recipe.setTitle(updatedRecipe.getTitle());			
		}
		if(updatedRecipe.getDescription() != null && !updatedRecipe.getDescription().equals(recipe.getDescription())) {
			recipe.setDescription(updatedRecipe.getDescription());			
		}
		recipeRepository.save(recipe);
		return recipe;
	}

	@Override
	public Set<LimitingFactor> getLFfromRecipe(Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
		List<Ingredient> ingredients = recipe.
				getIngredients().stream().map(e -> 
				e.getIngredientId()).toList();
		
		Set<LimitingFactor> limits = new HashSet<>();
		for(Ingredient ing : ingredients) { 
			for(LimitingIngredient limIng : ing.getLimitingFactors()) {
				limits.add(limIng.getLimitingFactor());
			}
		}
	
		return limits;
	}
	
	@Override
	public RecipeDTO getRecipeLf(Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
		Set<LimitingFactor> limits = new HashSet<>(extractLF(recipe));
		Map<String, Float> nutrition = new HashMap<>(calculateNutrition(recipe));
		RecipeDTO retVal = new RecipeDTO();
		retVal.setId(id);
		retVal.setAmount(recipe.getAmount());
		retVal.setDescription(recipe.getDescription());
		retVal.setSteps(recipe.getSteps());
		retVal.setTitle(recipe.getTitle());
		retVal.setTimeToPrepare(recipe.getTimeToPrepare());
		retVal.setLimitingFactors(limits);
		retVal.setIngredients(recipe.getIngredients());
		retVal.setNutrition(nutrition);
		// vrati recept i njegove alergene
		return retVal;
	}

	
	
	
	

}
