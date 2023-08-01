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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.dto.RecipeRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.LimitingIngredient;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.models.entities.RecipeIngredient;
import com.praksa.KitchenBackEnd.models.entities.RegularUser;
import com.praksa.KitchenBackEnd.repositories.CookRepository;
import com.praksa.KitchenBackEnd.repositories.IngredientRepository;
import com.praksa.KitchenBackEnd.repositories.LimitingIngredientRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeIngredientRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;
import com.praksa.KitchenBackEnd.repositories.RegularUserRepository;
import com.praksa.KitchenBackEnd.repositories.UserRepository;

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
	
	@Autowired
	private UserRepository userRepo;
	
	private CookRepository cookRepository;
	
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
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		recipe.setAmount(newRecipe.getAmount());
		recipe.setCook(cook);
		recipe.setSteps(newRecipe.getSteps());
		recipe.setTimeToPrepare(newRecipe.getTimeToPrepare());
		recipe.setTitle(newRecipe.getTitle());
		recipe.setDescription(newRecipe.getDescription());
		recipe.setIngredients(newRecipe.getIngredients());
		recipe.setCategory(newRecipe.getCategory());
		recipeRepository.save(recipe);
		return newRecipe;
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
	public RecipeDTO getRecipeLfandNutrition(Long id) {
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

	@Override
	public RecipeRegisterDTO createRecipeWithIng(RecipeRegisterDTO dto, Long cookId) {
		
		Recipe recipe = new Recipe();
		Cook cook = (Cook) userRepo.findById(cookId).get();
		List<RecipeIngredient> recIng = new ArrayList<>();
		Integer amount = 0;
		
		recipe.setDescription(dto.getDescription());
		recipe.setSteps(dto.getSteps());
		recipe.setTitle(dto.getTitle());
		recipe.setCategory(dto.getCategory());
		recipe.setTimeToPrepare(dto.getTimeToPrepare());
		recipe.setCook(cook);
		
		for (Map.Entry<Long, Integer> entry : dto.getIngredientMap().entrySet()) {
			RecipeIngredient ring = new RecipeIngredient();
			ring.setIngredientId(ingredientRepository.findById(entry.getKey()).get());
			ring.setAmount(entry.getValue());
			ring.setRecipeId(recipe);
			recIng.add(ring);
			amount += entry.getValue();
		}
		
		
		recipe.setAmount(amount);
		recipe.setIngredients(recIng);
		
		//sacuvajrecept
		recipeRepository.save(recipe);
		
		//sacuvaj recipeingredient
		recipeIngreRepo.saveAll(recIng);
		return dto;
		
		
//		int i = 0;
//		for (RecipeIngredient ri : dto.getRecipeIngredient()) {			
//			Ingredient ing = ingredientRepository.findById(ri.getIngredientId().getId()).get();
//			Integer ingredientAmount = ri.getAmount();
//			RecipeIngredient newIng = new RecipeIngredient(null, recipe, ing, ingredientAmount);
//			recIng.add(newIng);
//			amount =+ ingredientAmount;
//		}
		
		// u oba slucaja vraca null pointer 
		
//		for (RecipeIngredient ri : dto.getRecipeIngredient()) {
//			recIng.add(i++, new RecipeIngredient(null, recipe,
//							ingredientRepository.findById(ri.getIngredientId().getId()).get(),
//							ri.getAmount()));
//			amount += ri.getAmount();
//		}
		
	}

	
	
	
	
	
	

}
