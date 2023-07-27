package com.praksa.KitchenBackEnd.services;


import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.entities.Recipe;

public interface RecipeService {
	
	
	public Recipe createRecipe(RecipeDTO dto);
	
	
}
