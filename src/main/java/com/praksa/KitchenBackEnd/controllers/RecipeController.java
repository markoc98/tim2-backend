package com.praksa.KitchenBackEnd.controllers;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.dto.RecipeRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;
import com.praksa.KitchenBackEnd.services.RecipeService;

@RestController
@RequestMapping(path = "/api/v1/project")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	
	// Test endpoint za "/home", za neulogovanog korisnika
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<?> getAllRecipes() {
		return new ResponseEntity<>(recipeService.getRecipes(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/recipes")
	public ResponseEntity<?> getRecipes() {
		return new ResponseEntity<>(recipeService.getRecipes(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/recipes/{id}")
	public ResponseEntity<?> getRecipe(@PathVariable Long id) {
		return new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping(path = "/recipes/{id}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
		return new ResponseEntity<>(recipeService.deleteRecipe(id), HttpStatus.OK);
	}
	
	@PutMapping(path = "/recipes/{id}")
	public ResponseEntity<?> updateRecipe(@RequestBody RecipeDTO recipe, @PathVariable Long id) {
		return new ResponseEntity<>(recipeService.updateRecipe(recipe, id), HttpStatus.OK);
	}
	
	@PostMapping(path = "/recipes/addRec/{cookId}")
	public ResponseEntity<?> createRecipe(@RequestBody RecipeRegisterDTO recipe, @PathVariable Long cookId) {
		return new ResponseEntity<>(recipeService.createRecipeWithIng(recipe, cookId), HttpStatus.OK);
	}
	
	//------------------DINAMICKA PRETRAGA ALERGENA I HRANLJIVOSTI-------------------------------//
	
	
	@GetMapping(path = "/recipeLF/{recId}")
	public ResponseEntity<?> getRecipeAndLF(@PathVariable Long recId) {
		return new ResponseEntity<RecipeDTO>(recipeService.getRecipeLfandNutrition(recId), HttpStatus.OK);
	}
	
	
}

