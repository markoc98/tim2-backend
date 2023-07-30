package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	LimitingIngredientRepository limIngredientRepo;

	@Autowired 
	private CookService cookService;

	// recimo da se kuvar ulogovao i da mozemo da izvucemo njegov id iz tokena
	// Morao dodati cookId, zbog prosledjivanja servisu
	public Recipe createRecipe(RecipeDTO newRecipe, Long cookId) {
		Recipe recipe = new Recipe();
		Cook cook = cookService.getCook(cookId);
		recipe.setAmount(newRecipe.getAmount());
		recipe.setCook(cook);
		recipe.setSteps(newRecipe.getSteps());
		recipe.setTimeToPrepare(newRecipe.getTimeToPrepare());
		recipe.setTitle(newRecipe.getTitle());
		recipe.setDescription(newRecipe.getDescription());
		return recipe;
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
//		List<Ingredient> ingredients = new ArrayList<>();
//		for(RecipeIngredient ri : recipe.getIngredients()) {
//			ingredients.add(ri.getIngredientId());
//		}
		List<Ingredient> ingredients = recipe.
				getIngredients().stream().map(e -> 
				e.getIngredientId()).toList();
		
		Set<LimitingFactor> limits = new HashSet<>();
		for(Ingredient ing : ingredients) { 
			for(LimitingIngredient limIng : ing.getLimitingFactor()) {
				limits.add(limIng.getLimitingFactor());
			}
		}
	
		return limits;
	}
	
	@Override
	public RecipeDTO getRecipeLf(Long id) {
		
		//Instanciraj recept
		Recipe recipe =  recipeRepository.findById(id).get();
		
		// Izvuci sastojke iz recepta
		List<Ingredient> ingredients = recipe.
				getIngredients().stream().map(e ->
				e.getIngredientId()).toList();
		
		// Izvicu alergene iz sastojaka
		Set<LimitingFactor> limits = new HashSet<>();
		for(Ingredient ing : ingredients) { 
			for(LimitingIngredient limIng : ing.getLimitingFactor()) {
				limits.add(limIng.getLimitingFactor());
			}
		}
		// uvuci sve to u dto
		RecipeDTO retVal = new RecipeDTO();
		retVal.setId(id);
		retVal.setAmount(recipe.getAmount());
		retVal.setDescription(recipe.getDescription());
		retVal.setSteps(recipe.getSteps());
		retVal.setTitle(recipe.getTitle());
		retVal.setTimeToPrepare(recipe.getTimeToPrepare());
		retVal.setLimitingFactors(limits);
		// vrati recept i njegove alergene
		return retVal;
	}
	
	

}
