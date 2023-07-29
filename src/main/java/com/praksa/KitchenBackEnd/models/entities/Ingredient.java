package com.praksa.KitchenBackEnd.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Version;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	@JsonIgnore
	private Integer version;
	
	
	private String name;
	private String unit;
	private Integer calories;
	private Float carbs;
	private Float sugars;
	private Float fats;
	@Column(name = "saturated_fats")
	private Float saturatedFats;
	private Float proteins;
	
	@OneToMany(mappedBy = "ingredients", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<LimitingIngredient> limitingFactor = new ArrayList<>();
	@JsonIgnore
	@JsonBackReference
	@OneToMany(mappedBy = "ingredientId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<RecipeIngredient> recipes = new ArrayList<>();

	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ingredient(Long id, Integer version, String name, String unit, Integer calories, Float carbs, Float sugars,
			Float fats, Float saturatedFats, Float proteins, List<LimitingIngredient> limitingFactor,
			List<RecipeIngredient> recipes) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.unit = unit;
		this.calories = calories;
		this.carbs = carbs;
		this.sugars = sugars;
		this.fats = fats;
		this.saturatedFats = saturatedFats;
		this.proteins = proteins;
		this.limitingFactor = limitingFactor;
		this.recipes = recipes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Float getCarbs() {
		return carbs;
	}

	public void setCarbs(Float carbs) {
		this.carbs = carbs;
	}

	public Float getSugars() {
		return sugars;
	}

	public void setSugars(Float sugars) {
		this.sugars = sugars;
	}

	public Float getFats() {
		return fats;
	}

	public void setFats(Float fats) {
		this.fats = fats;
	}

	public Float getSaturatedFats() {
		return saturatedFats;
	}

	public void setSaturatedFats(Float saturatedFats) {
		this.saturatedFats = saturatedFats;
	}

	public Float getProteins() {
		return proteins;
	}

	public void setProteins(Float proteins) {
		this.proteins = proteins;
	}

	public List<LimitingIngredient> getLimitingFactor() {
		return limitingFactor;
	}

	public void setLimitingFactor(List<LimitingIngredient> limitingFactor) {
		this.limitingFactor = limitingFactor;
	}

	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeIngredient> recipes) {
		this.recipes = recipes;
	}

	

}
