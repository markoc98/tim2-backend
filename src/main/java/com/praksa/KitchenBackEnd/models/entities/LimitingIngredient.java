package com.praksa.KitchenBackEnd.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LimitingIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredientId")
	private Ingredient ingredients;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "limitingFactorId")
	private LimitingFactor limitingFactor;

	public LimitingIngredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitingIngredient(Long id, Ingredient ingredients, LimitingFactor limitingFactor) {
		super();
		this.id = id;
		this.ingredients = ingredients;
		this.limitingFactor = limitingFactor;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ingredient getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredient ingredients) {
		this.ingredients = ingredients;
	}

	public LimitingFactor getLimitingFactor() {
		return limitingFactor;
	}

	public void setLimitingFactor(LimitingFactor limitingFactor) {
		this.limitingFactor = limitingFactor;
	}
	
	
}
