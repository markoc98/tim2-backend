package com.praksa.KitchenBackEnd.controllers.dto;

import com.praksa.KitchenBackEnd.entities.EUserRole;

public class AdminRegisterDTO extends UserRegisterDTO {

	public AdminRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminRegisterDTO(String username, String password, EUserRole role) {
		super(username, password, role);
		// TODO Auto-generated constructor stub
	}
	
	
}
