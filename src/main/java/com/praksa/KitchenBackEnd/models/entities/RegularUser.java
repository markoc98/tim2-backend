package com.praksa.KitchenBackEnd.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "regular_users")
public class RegularUser extends User {
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	//TODO: napraviti veze kada ostali entiteti dodju

	public RegularUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegularUser(Long id, String username, String password, EUserRole role, Integer version) {
		super(id, username, password, role, version);
		// TODO Auto-generated constructor stub
	}

	public RegularUser(String firstName, String lastName) {
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
