package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.repositories.IngredientRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	
	@Override
	public Recipe createRecipe(RecipeDTO dto) {
		
		return null;
	}
	
}
