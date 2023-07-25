package com.praksa.KitchenBackEnd.controllers.dto;

import com.praksa.KitchenBackEnd.entities.EUserRole;

public class UserRegisterDTO {
	
	private String username;
	
	private String password;
	
	private EUserRole role;

	public UserRegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegisterDTO(String username, String password, EUserRole role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EUserRole getRole() {
		return role;
	}

	public void setRole(EUserRole role) {
		this.role = role;
	}
	
	
}
