package com.praksa.KitchenBackEnd.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LikedRecipes {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column
	public Integer rating;
	
	
	@JsonBackReference(value = "regularUser-likedRecipes")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "regular_users_id")
	public RegularUser regularUser;
	
	
	@JsonBackReference(value = "recipes-likedRecipes")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id")
	public Recipe recipe;

	public LikedRecipes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikedRecipes(Long id, Integer rating, RegularUser regularUser, Recipe recipe) {
		super();
		this.id = id;
		this.rating = rating;
		this.regularUser = regularUser;
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public RegularUser getRegularUser() {
		return regularUser;
	}

	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	
	
}
