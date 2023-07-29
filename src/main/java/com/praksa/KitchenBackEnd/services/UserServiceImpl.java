package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.controllers.factory.UserFactory;
import com.praksa.KitchenBackEnd.models.dto.AdminRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.CookRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.RegularUserRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.Administrator;
import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.models.entities.RegularUser;
import com.praksa.KitchenBackEnd.models.entities.User;
import com.praksa.KitchenBackEnd.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/*----------------GET--------------------*/
	@Override
	public Iterable<User> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public RegularUser getUserById(Long id) {
		RegularUser user = (RegularUser) userRepository.findById(id).get();
		return user;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
	
	
	
	
	/*------------------POST-----------------*/
	@Override
	public RegularUser addUser(RegularUserRegisterDTO dto) {
		RegularUser regUser = (RegularUser) UserFactory.createUser(dto);
		userRepository.save(regUser);
		return regUser;
	}

	@Override
	public Administrator addAdmin(AdminRegisterDTO dto) {
		Administrator admin = (Administrator) UserFactory.createUser(dto);
		userRepository.save(admin);
		return admin;
	}

	@Override
	public Cook addCook(CookRegisterDTO dto) {
		Cook cook = (Cook) UserFactory.createUser(dto);
		userRepository.save(cook);
		return cook;
	}

	
	
	
	
}
