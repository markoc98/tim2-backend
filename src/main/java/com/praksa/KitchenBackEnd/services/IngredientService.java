package com.praksa.KitchenBackEnd.services;

import com.praksa.KitchenBackEnd.models.dto.IngredientDTO;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;

public interface IngredientService {

	
	
	public Ingredient addIngredient(IngredientDTO ingredient);
	public Ingredient updateIngredient(IngredientDTO ingredient, Long id);
    public Ingredient getIngredientById(Long id);
	public Iterable<Ingredient> getAllIngredients();
	public Ingredient deleteIngredient(Long id);
}
