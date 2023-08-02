package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.LikedRecipes;

public interface LikedRecipesRepository extends CrudRepository<LikedRecipes, Long> {

}
