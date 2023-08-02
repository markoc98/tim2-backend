package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	
	@Autowired
	private CookRepository cookRepository;
	
	
	
	
	//=-=-=-=-=-=-=-=-=-=-=FUNKCIONALNA DEKONSTRUKCIJA=-=-=-=-=-=-=-=-=-=-=-///
	
	//IZLVACENJE ALERGENA
	private Set<String> extractLF(Recipe recipe) {
		
		List<Ingredient> ingredients = recipe.
				getIngredients().stream().map(e -> 
				e.getIngredientId()).toList();
		
		Set<String> limits = new HashSet<>();
		
		for(Ingredient ing : ingredients) { 
			for(LimitingIngredient limIng : ing.getLimitingFactors()) {
				limits.add(limIng.getLimitingFactor().getName());
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
	
	
	
	//MAPIRANJE IMENA I KOLICINE SASTOJAKA
	private Map<String, Integer> ingredientNamedMap(Recipe recipe) {
		List<RecipeIngredient> recIng = recipe.getIngredients();
		Map<String, Integer> namedIng = new HashMap<>();
		for (RecipeIngredient ri : recIng) {
			namedIng.put(ingredientRepository.findById(ri.getIngredientId().getId()).get().getName(),
					ri.getAmount());
		}
		return namedIng;
	}
	
	//MAPIRANJE IMENA I KOLICINE SASTOJAKA SA DODATOM JEDINICOM MERE
	private Map<String, String> ingredientNamedMapString(Recipe recipe) {
		List<RecipeIngredient> recIng = recipe.getIngredients();
		Map<String, String> namedIng = new HashMap<>();
		//ENACT PARADOX
		for (RecipeIngredient ri : recIng) {
			namedIng.put(ingredientRepository.findById(ri.getIngredientId().getId()).get().getName(), //<-key
					ri.getAmount().toString() + ingredientRepository.findById(ri.getIngredientId().getId()).get().getUnit()); //<-value
		}
		return namedIng;
	}
	
	
	//=-=-==-=-==-=-=-=-==-=-==SERVICES-=-=-==-=-==-=-=-=-==-=-===-=-=-=-==-=-==-=-=//
	
	
	
	//da li promenuti iterator da vrati recepte formatirane kao dto u getRecipe
	//i da li je to dobra ideja?
	@Override
	public Iterable<Recipe> getRecipes() {
		return recipeRepository.findAll();
	}
	
	
	@Override
	public RecipeRegisterDTO getRecipe(Long id) {
		
		Recipe recipe = recipeRepository.findById(id).get();
		RecipeRegisterDTO dto = new RecipeRegisterDTO();
		
		dto.setId(recipe.getId());
		dto.setCategory(recipe.getCategory());
		dto.setDescription(recipe.getDescription());
		dto.setSteps(recipe.getSteps());
		dto.setTitle(recipe.getTitle());
		dto.setTimeToPrepare(recipe.getTimeToPrepare());
		dto.setAmount(recipe.getAmount());
		dto.setCook(recipe.getCook().getFirstName() + " " + recipe.getCook().getLastName());
		dto.setNutrition(calculateNutrition(recipe));
		dto.setCreatedOn(recipe.getCreatedOn());
		dto.setUpdatedOn(recipe.getUpdatedOn());
//		dto.setIngredients(extractIng(recipe);
		dto.setIngredientAmount(ingredientNamedMapString(recipe));
		dto.setLimitingFactors(extractLF(recipe));
		
		return dto;
	}

	@Override
	public Recipe deleteRecipe(Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
		recipeRepository.delete(recipe);
		return recipe;
	}
	


	@Override
	public Recipe updateRecipe(RecipeRegisterDTO updatedRecipe, Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
		List<RecipeIngredient> recIng = recipeIngreRepo.findAllByRecipeId(recipe);
		Integer amount = 0;
		
		//Problem - promena kolicine znaci promena kolicine svih sastojaka
		//Resenje - mozda ingredientMapper za svaki sastojak zasebno?
		
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
		if(updatedRecipe.getCategory() != null && !updatedRecipe.getCategory().equals(recipe.getCategory())) {
			recipe.setCategory(updatedRecipe.getCategory());
		}
		for (Map.Entry<Long, Integer> entry : updatedRecipe.getIngredientMap().entrySet()) {
			for (RecipeIngredient ring : recIng) {
				if(entry.getValue() != null && !ring.getAmount().equals(entry.getValue())) {
					if(entry.getValue() < ring.getAmount()) {
						recipe.setAmount(recipe.getAmount() - entry.getValue());
					}
					ring.setAmount(entry.getValue());
					recipe.setAmount(recipe.getAmount() + entry.getValue());
				}
				
			}
			
		}
		
		
		
		
		recipeRepository.save(recipe);
		recipeIngreRepo.saveAll(recIng);
		return recipe;
	}
	
	@Override
	public RecipeRegisterDTO createRecipe(RecipeRegisterDTO dto, Long cookId) {
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
		
		
		recipeRepository.save(recipe);
		recipeIngreRepo.saveAll(recIng);
		return dto;
		
		
		// u oba slucaja vraca null vrednost posto je RecipeIngredient entitet sam po sebi bogalj
		// sacuvano da proucim 
//		int i = 0;
//		for (RecipeIngredient ri : dto.getRecipeIngredient()) {			
//			Ingredient ing = ingredientRepository.findById(ri.getIngredientId().getId()).get();
//			Integer ingredientAmount = ri.getAmount();
//			RecipeIngredient newIng = new RecipeIngredient(null, recipe, ing, ingredientAmount);
//			recIng.add(newIng);
//			amount += ingredientAmount;
//		}
		
//		for (RecipeIngredient ri : dto.getRecipeIngredient()) {
//			recIng.add(i++, new RecipeIngredient(null, recipe,
//							ingredientRepository.findById(ri.getIngredientId().getId()).get(),
//							ri.getAmount()));
//			amount += ri.getAmount();
//		}
		
	}
	
	
	
	//mozda zatreba
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
	
	
	//ostavicu za sada ali treba obrisati
	@Override
	public RecipeDTO getRecipeLfandNutrition(Long id) {
		Recipe recipe = recipeRepository.findById(id).get();
//		Set<LimitingFactor> limits = new HashSet<>(extractLF(recipe));
		Map<String, Float> nutrition = new HashMap<>(calculateNutrition(recipe));
		RecipeDTO retVal = new RecipeDTO();
		retVal.setId(id);
		retVal.setAmount(recipe.getAmount());
		retVal.setDescription(recipe.getDescription());
		retVal.setSteps(recipe.getSteps());
		retVal.setTitle(recipe.getTitle());
		retVal.setTimeToPrepare(recipe.getTimeToPrepare());
//		retVal.setLimitingFactors(limits);
		retVal.setIngredients(recipe.getIngredients());
		retVal.setNutrition(nutrition);
		// vrati recept i njegove alergene
		return null;
	}

	

	
	
	
	
	
	

}
