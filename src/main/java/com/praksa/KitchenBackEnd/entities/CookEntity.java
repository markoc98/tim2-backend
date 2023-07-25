package com.praksa.KitchenBackEnd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cooks")
public class CookEntity extends UserEntity {
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	
	//TODO: Vezati ostale entitete kada budu napravljeni

	
	public CookEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CookEntity(Long id, String username, String password, EUserRole role, Integer version) {
		super(id, username, password, role, version);
		// TODO Auto-generated constructor stub
	}

	public CookEntity(String firstName, String lastName) {
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
