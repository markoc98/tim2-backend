package com.praksa.KitchenBackEnd.services;

import java.util.Set;

import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.Recipe;

public interface RegularUserService {
	
	public Set<LimitingFactor> getLimitingFactors(Long id);
	public LimitingFactor addLimitingFactor(Long userId, Long lfId);
	public LimitingFactor removeLimitingFactor(Long userId, Long lfId);
	
	public Set<Recipe> getRecipes();
	public Recipe addRecipe(Long userId, Long recId);
	public Recipe removeRecipe(Long userId, Long recId);
}
