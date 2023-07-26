package com.praksa.KitchenBackEnd.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cooks")
public class Cook extends User {
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	//TODO: Vezati ostale entitete kada budu napravljeni
	
	@Column
	@JsonBackReference
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
