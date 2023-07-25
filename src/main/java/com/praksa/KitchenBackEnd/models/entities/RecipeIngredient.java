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
	
	public RecipeIngredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeIngredient(Long id, Long recipeId, Long ingredientId, Integer amount) {
		super();
		Id = id;
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
		this.amount = amount;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
