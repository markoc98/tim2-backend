package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	
	
	public Ingredient findByNameStartsWith(String name);
}
