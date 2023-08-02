package com.praksa.KitchenBackEnd.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.models.entities.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {

	public List<RecipeIngredient> findByIngredientId(Ingredient ingredient);
	public List<RecipeIngredient> findAllByRecipeId(Recipe recipe);
	
}
