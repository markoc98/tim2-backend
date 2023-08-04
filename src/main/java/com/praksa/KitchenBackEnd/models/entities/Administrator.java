package com.praksa.KitchenBackEnd.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrators")
public class Administrator extends User {
	
	
	
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrator(Long id, String username, String password, EUserRole role, Integer version) {
		super(id, username, password, role, version);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
