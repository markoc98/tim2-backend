package com.praksa.KitchenBackEnd.models.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LimitingFactor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Integer version;
	private String name;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "ingredient")
	private Ingredient ingredient;
	
	@ManyToMany(mappedBy = "limitingFactor")
	private Set<RegularUser> affectedUser = new HashSet<>();

	public LimitingFactor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitingFactor(Long id, Integer version, String name, Ingredient ingredient, Set<RegularUser> affectedUser) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.ingredient = ingredient;
		this.affectedUser = affectedUser;
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

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Set<RegularUser> getAffectedUser() {
		return affectedUser;
	}

	public void setAffectedUser(Set<RegularUser> affectedUser) {
		this.affectedUser = affectedUser;
	}
	
	
	
	
}
