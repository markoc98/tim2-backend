package com.praksa.KitchenBackEnd.services;

import java.util.Set;

import com.praksa.KitchenBackEnd.models.entities.AffectedUsers;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.Recipe;

public interface RegularUserService {
	
	public Set<LimitingFactor> getLimitingFactors(Long userId);
	public AffectedUsers addLimitingFactor(Long userId, Long lfId);
	public AffectedUsers removeLimitingFactor(Long lfId);
	
	public Set<Recipe> getRecipes(Long userId);
	public Recipe addRecipe(Long userId, Long recId);
	public Recipe removeRecipe(Long userId, Long recId);
}
