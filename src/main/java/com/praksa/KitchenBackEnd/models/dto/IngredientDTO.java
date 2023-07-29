package com.praksa.KitchenBackEnd.models.dto;

import javax.persistence.Column;

public class IngredientDTO {
	
	private String name;
	private String unit;
	private Integer calories;
	private Integer carbs;
	private Integer sugars;
	private Integer fats;
	private Integer saturatedFats;
	private Integer proteins;
	
	
	public IngredientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IngredientDTO(String name, String unit, Integer calories, Integer carbs, Integer sugars, Integer fats,
			Integer saturatedFats, Integer proteins) {
		super();
		this.name = name;
		this.unit = unit;
		this.calories = calories;
		this.carbs = carbs;
		this.sugars = sugars;
		this.fats = fats;
		this.saturatedFats = saturatedFats;
		this.proteins = proteins;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getCalories() {
		return calories;
	}
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	public Integer getCarbs() {
		return carbs;
	}
	public void setCarbs(Integer carbs) {
		this.carbs = carbs;
	}
	public Integer getSugars() {
		return sugars;
	}
	public void setSugars(Integer sugars) {
		this.sugars = sugars;
	}
	public Integer getFats() {
		return fats;
	}
	public void setFats(Integer fats) {
		this.fats = fats;
	}
	public Integer getSaturatedFats() {
		return saturatedFats;
	}
	public void setSaturatedFats(Integer saturatedFats) {
		this.saturatedFats = saturatedFats;
	}
	public Integer getProteins() {
		return proteins;
	}
	public void setProteins(Integer proteins) {
		this.proteins = proteins;
	}
	
	
	

}
