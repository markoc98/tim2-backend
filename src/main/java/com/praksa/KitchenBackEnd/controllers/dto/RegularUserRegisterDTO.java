package com.praksa.KitchenBackEnd.controllers.dto;

import com.praksa.KitchenBackEnd.entities.EUserRole;

public class RegularUserRegisterDTO extends UserRegisterDTO {
	
	private String firstName;
	
	private String lastName;

	public RegularUserRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegularUserRegisterDTO(String username, String password, EUserRole role) {
		super(username, password, role);
		// TODO Auto-generated constructor stub
	}

	public RegularUserRegisterDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	
	
	
}
