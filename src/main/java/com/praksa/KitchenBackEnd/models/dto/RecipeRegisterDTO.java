package com.praksa.KitchenBackEnd.models.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.praksa.KitchenBackEnd.models.entities.ERecipeCategory;
import com.praksa.KitchenBackEnd.models.entities.RecipeIngredient;

public class RecipeRegisterDTO {
	
	@JsonIgnore
	private Long id;
	private String title;
	private String description;
	private String steps;
	private Integer amount;
	private Integer timeToPrepare;
	private ERecipeCategory category;

	private Map<Long, Integer> ingredientMap = new HashMap<>();
	
	private List<Integer> ingredientAmount;
	private List<Long> ingredientId;
	private List<RecipeIngredient> recipeIngredient;
	
	public RecipeRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeRegisterDTO(Long id, String title, String description, String steps, Integer amount,
			Integer timeToPrepare, ERecipeCategory category, Map<Long, Integer> ingredientMap,
			List<Integer> ingredientAmount, List<Long> ingredientId, List<RecipeIngredient> recipeIngredient) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.steps = steps;
		this.amount = amount;
		this.timeToPrepare = timeToPrepare;
		this.category = category;
		this.ingredientMap = ingredientMap;
		this.ingredientAmount = ingredientAmount;
		this.ingredientId = ingredientId;
		this.recipeIngredient = recipeIngredient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTimeToPrepare() {
		return timeToPrepare;
	}

	public void setTimeToPrepare(Integer timeToPrepare) {
		this.timeToPrepare = timeToPrepare;
	}

	public ERecipeCategory getCategory() {
		return category;
	}

	public void setCategory(ERecipeCategory category) {
		this.category = category;
	}

	public Map<Long, Integer> getIngredientMap() {
		return ingredientMap;
	}

	public void setIngredientMap(Map<Long, Integer> ingredientMap) {
		this.ingredientMap = ingredientMap;
	}

	public List<Integer> getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(List<Integer> ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	public List<Long> getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(List<Long> ingredientId) {
		this.ingredientId = ingredientId;
	}

	public List<RecipeIngredient> getRecipeIngredient() {
		return recipeIngredient;
	}

	public void setRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}

	
	
	
}
