package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.repositories.UserRepository;


@Service
public class RegularUserServiceImpl implements RegularUserService {
	
	@Autowired
	private UserRepository userRepository;
	
}
