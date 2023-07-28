package com.praksa.KitchenBackEnd.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cooks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cook extends User {
	
	@Column(nullable = false)
	@NotBlank(message = "First name must be provided.")
	@Size(min = 2, max = 30, message = "First name must be bewtween {min} and {max} characters long.")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "Last name must be provided")
	@Size(min= 2, max = 30, message = "Last name must be between {min} and {max} characters long.")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotNull(message = "Email must be provided")
	@Email(message = "Email is not valid" )
	private String email;
	
	@Column(columnDefinition = "TEXT")
	private String aboutMe;
	
	
	@JsonBackReference
	@Column
	@OneToMany(mappedBy = "cook", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Recipe> recipes = new ArrayList<>();

	public Cook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cook(Long id, String username, String password, EUserRole role, Integer version) {
		super(id, username, password, role, version);
		// TODO Auto-generated constructor stub
	}

	public Cook(String firstName, String lastName, List<Recipe> recipes) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.recipes = recipes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	

	
	
	
	
	
}
