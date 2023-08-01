package com.praksa.KitchenBackEnd.services;


import java.util.List;
import java.util.Set;

import com.praksa.KitchenBackEnd.models.dto.RecipeDTO;
import com.praksa.KitchenBackEnd.models.dto.RecipeRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.Recipe;

public interface RecipeService {
	
	
	public Iterable<Recipe> getRecipes();
	public Recipe getRecipe(Long id);
	public Recipe deleteRecipe(Long id);
	public Recipe updateRecipe(RecipeDTO recipe, Long id);
	
	public RecipeRegisterDTO createRecipeWithIng(RecipeRegisterDTO dto, Long cookId);
	
	public RecipeDTO getRecipeLfandNutrition(Long id);
//	public RecipeDTO getRecipeCaloricValue(Long id);
	
	public Set<LimitingFactor> getLFfromRecipe(Long id);
	
}
