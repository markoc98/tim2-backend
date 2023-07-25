package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.praksa.KitchenBackEnd.repositories.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
}
