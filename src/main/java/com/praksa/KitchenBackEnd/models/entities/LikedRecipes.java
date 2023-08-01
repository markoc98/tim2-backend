package com.praksa.KitchenBackEnd.models.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LikedRecipes {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long id;
	
	
	
	@JsonBackReference(value = "regularUser-likedRecipes")
	@OneToOne(mappedBy = "likedRecipes")
	public RegularUser regularUser;
	
	
	@JsonManagedReference(value = "likedRecipes-recipes")
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	public Set<Recipe> recipe = new HashSet<>();


	public LikedRecipes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LikedRecipes(Long id, RegularUser regularUser, Set<Recipe> recipe) {
		super();
		this.id = id;
		this.regularUser = regularUser;
		this.recipe = recipe;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public RegularUser getRegularUser() {
		return regularUser;
	}


	public void setRegularUser(RegularUser regularUser) {
		this.regularUser = regularUser;
	}


	public Set<Recipe> getRecipe() {
		return recipe;
	}


	public void setRecipe(Set<Recipe> recipe) {
		this.recipe = recipe;
	}



	
	
	
}
