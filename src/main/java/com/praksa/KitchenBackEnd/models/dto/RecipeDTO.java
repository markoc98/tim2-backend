package com.praksa.KitchenBackEnd.models.dto;

import java.util.List;
import java.util.Set;

import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;

public class RecipeDTO {
	
	private Long id;
	private String title;
	private String description;
	private String steps;
	private Integer amount;
	private Integer timeToPrepare;
	private Set<LimitingFactor> limitingFactors;
	public RecipeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecipeDTO(Long id, String title, String description, String steps, Integer amount, Integer timeToPrepare,
			Set<LimitingFactor> limitingFactors) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.steps = steps;
		this.amount = amount;
		this.timeToPrepare = timeToPrepare;
		this.limitingFactors = limitingFactors;
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
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getTimeToPrepare() {
		return timeToPrepare;
	}
	public void setTimeToPrepare(Integer timeToPrepare) {
		this.timeToPrepare = timeToPrepare;
	}
	public Set<LimitingFactor> getLimitingFactors() {
		return limitingFactors;
	}
	public void setLimitingFactors(Set<LimitingFactor> limitingFactors) {
		this.limitingFactors = limitingFactors;
	}
	
	
	
	

	
	
}
