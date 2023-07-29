package com.praksa.KitchenBackEnd.services;

import com.praksa.KitchenBackEnd.models.dto.AdminRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.CookRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.RegularUserRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.Administrator;
import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.models.entities.RegularUser;
import com.praksa.KitchenBackEnd.models.entities.User;

public interface UserService {
	
	/*------------GET----------------*/
	
	public Iterable<User> getAll();
	public RegularUser getUserById(Long id);

	public User getUserByUsername(String username);

	
	/*------------POST-------------*/
	
	public RegularUser addUser(RegularUserRegisterDTO dto);
	public Administrator addAdmin(AdminRegisterDTO dto);
	public Cook addCook(CookRegisterDTO dto);
	
	/*-----------DELETE-------------*/
	
	public RegularUser deleteUser(RegularUserRegisterDTO dto);
	
	/*------------PUT--------------*/
	
	public RegularUser changeUser(RegularUserRegisterDTO dto);
	
}
