package com.praksa.KitchenBackEnd.models.dto;



public class LimFactorDTO {
	
	String name;
	
	private Long ingredientId;
    
	
	
	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public LimFactorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LimFactorDTO(String name, Long ingredientId) {
		super();
		this.name = name;
		this.ingredientId = ingredientId;
	}
	
	
	
}
