package com.praksa.KitchenBackEnd.models.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RecipeIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@Column(name = "recipe_id")
	private Long recipeId;
	@Column(name = "ingredient_id")
	private Long ingredientId;
	private Integer amount;
}
