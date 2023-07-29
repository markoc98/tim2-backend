package com.praksa.KitchenBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.KitchenBackEnd.models.dto.UserRegisterDTO;
import com.praksa.KitchenBackEnd.services.UserService;

@RestController
@RequestMapping(path = "api/v1/project/regUser")
public class RegularUserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping( method = RequestMethod.POST, path = "/userRegister")
	public ResponseEntity<?> userRegister(UserRegisterDTO dto) {
		return null;
	}
	
}
