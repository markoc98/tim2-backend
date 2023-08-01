package com.praksa.KitchenBackEnd.services;


import java.util.List;
import java.util.Set;

import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.Recipe;

public interface RecipeService {
	
	
	public RecipeDTO createRecipe(RecipeDTO dto, Long id);
	public Iterable<Recipe> getRecipes();
	public Recipe getRecipe(Long id);
	public Recipe deleteRecipe(Long id);
	public Recipe updateRecipe(RecipeDTO recipe, Long id);
	public Recipe addRecipeToUser(Long userId, Long recipeId);
	
	public RecipeDTO getRecipeLf(Long id);
//	public RecipeDTO getRecipeCaloricValue(Long id);
	
	public Set<LimitingFactor> getLFfromRecipe(Long id);
	
}
