package com.praksa.KitchenBackEnd.services;


import java.util.List;

import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.entities.Recipe;

public interface RecipeService {
	
	
	public Recipe createRecipe(RecipeDTO dto, Long id);
	public Iterable<Recipe> getRecipes();
	public Recipe getRecipe(Long id);
	public Recipe deleteRecipe(Long id);
	public Recipe updateRecipe(RecipeDTO recipe, Long id);
	
}
