package com.praksa.KitchenBackEnd.models.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "regular_users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegularUser extends User {
	
	@Column(nullable = false)
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters long")
	private String firstName;
	
	@Column(nullable = false)
	@Size(min = 2, max = 30, message = "Last name must be between {min} and {max} characters long")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotNull(message = "Email must be provided")
	@Email(message = "Email is not valid")
	private String email;
	
	
	
	
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(
		name= "affected_users",
		joinColumns = @JoinColumn(name = "regular_users_id"),
		inverseJoinColumns = @JoinColumn(name = "limiting_factor_id"))
	private Set<LimitingFactor> limitingFactor = new HashSet<>();
	
	
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(
		name = "liked_recipes",
		joinColumns = @JoinColumn(name = "regular_users_id"),
		inverseJoinColumns = @JoinColumn(name = "recipes_id"))
	private Set<Recipe> likedRecipes = new HashSet<>();

	public RegularUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegularUser(Long id, String username, String password, EUserRole role, Integer version) {
		super(id, username, password, role, version);
		// TODO Auto-generated constructor stub
	}

	public RegularUser(String firstName, String lastName, Set<LimitingFactor> limitingFactor,
			Set<Recipe> likedRecipes) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.limitingFactor = limitingFactor;
		this.likedRecipes = likedRecipes;
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

	public Set<LimitingFactor> getLimitingFactor() {
		return limitingFactor;
	}

	public void setLimitingFactor(Set<LimitingFactor> limitingFactor) {
		this.limitingFactor = limitingFactor;
	}

	public Set<Recipe> getLikedRecipes() {
		return likedRecipes;
	}

	public void setLikedRecipes(Set<Recipe> likedRecipes) {
		this.likedRecipes = likedRecipes;
	}

	
	
	
	
	
	
	
}
