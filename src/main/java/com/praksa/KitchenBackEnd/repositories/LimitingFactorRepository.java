package com.praksa.KitchenBackEnd.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.LimitingIngredient;

public interface LimitingFactorRepository extends CrudRepository<LimitingFactor, Long> {

	
	public List<LimitingIngredient> findByIngredients(Ingredient ingredient);

}
