package com.praksa.KitchenBackEnd.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.LimitingIngredient;

public interface LimitingIngredientRepository extends CrudRepository<LimitingIngredient, Long> {
	
	
	public List<LimitingIngredient> findAllByIngredients(Long id);
}
