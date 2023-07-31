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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "recipes")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Recipe must have a title")
	@Size(min = 5, max = 100, message = "The title has to be between {min} and {max} characters long.")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "Recipe has to have a description.")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "You need to provide steps on how to prepare the meal.")
	private String steps;		
	
	@Column(name = "time_to_prepare")
	private Integer timeToPrepare;
	
	private Integer amount;
	
	@JsonManagedReference(value = "recipe-recipeIngredients")
	@OneToMany(mappedBy = "recipeId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<RecipeIngredient> ingredients = new ArrayList<>();
	
	@JsonManagedReference(value = "recipe-cook")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "cook")
	private Cook cook;
	
	@JsonIgnoreProperties
	@ManyToMany(mappedBy = "likedRecipes", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Set<RegularUser> recipes = new HashSet<>();
	
	@Version
	@JsonIgnore
	private Integer version;

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(Long id, String title, String description, String steps, Integer timeToPrepare, Integer amount,
			List<RecipeIngredient> ingredients, Cook cook, Set<RegularUser> recipes, Integer version) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.steps = steps;
		this.timeToPrepare = timeToPrepare;
		this.amount = amount;
		this.ingredients = ingredients;
		this.cook = cook;
		this.recipes = recipes;
		this.version = version;
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

	public void setDescription(String decription) {
		this.description = decription;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Integer getTimeToPrepare() {
		return timeToPrepare;
	}

	public void setTimeToPrepare(Integer timeToPrepare) {
		this.timeToPrepare = timeToPrepare;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Cook getCook() {
		return cook;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}

	public Set<RegularUser> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RegularUser> recipes) {
		this.recipes = recipes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	
}
