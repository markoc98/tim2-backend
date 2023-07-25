package com.praksa.KitchenBackEnd.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrators")
public class AdministratorEntity extends UserEntity {
	
	
	
	public AdministratorEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministratorEntity(Long id, String username, String password, EUserRole role, Integer version) {
		super(id, username, password, role, version);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
