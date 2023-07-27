package com.praksa.KitchenBackEnd.services;

import java.util.List;

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
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private CookService cookService;

	@Override
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
		recipeRepository.save(recipe);
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

}
