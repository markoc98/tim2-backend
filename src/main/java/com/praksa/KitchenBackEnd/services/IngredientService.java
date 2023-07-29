package com.praksa.KitchenBackEnd.services;

import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.entities.Ingredient;

@Service
public interface IngredientService {
	
	public Ingredient deleteIngredient(Long id);
}
