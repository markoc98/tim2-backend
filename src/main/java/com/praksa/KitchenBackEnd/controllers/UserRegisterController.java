package com.praksa.KitchenBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.KitchenBackEnd.controllers.factory.UserFactory;
import com.praksa.KitchenBackEnd.models.dto.AdminRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.CookRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.RegularUserRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.Administrator;
import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.models.entities.RegularUser;
import com.praksa.KitchenBackEnd.repositories.AdministratorRepository;
import com.praksa.KitchenBackEnd.repositories.CookRepository;
import com.praksa.KitchenBackEnd.repositories.RegularUserRepository;
import com.praksa.KitchenBackEnd.repositories.UserRepository;
import com.praksa.KitchenBackEnd.services.UserService;
import com.praksa.KitchenBackEnd.services.UserServiceImpl;

@RestController
@RequestMapping(path = "api/v1/project/register")
public class UserRegisterController {
	
	//TODO: Validacije, hvatanje gresaka, loggovanje?
	
	@Autowired
	UserService userService;
	
	
	//Proste metode za testiranje, razgranacemo ih blagovremeno
	
	@RequestMapping(method = RequestMethod.POST, value = "/admin")
	public ResponseEntity<?> registerAdmin(@RequestBody AdminRegisterDTO adminDTO) {
		
		return new ResponseEntity<>(userService.addAdmin(adminDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/regUser")
	public ResponseEntity<?> registerRegUser(@RequestBody RegularUserRegisterDTO regUserDTO) {
		
		return new ResponseEntity<>(userService.addUser(regUserDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/cook")
	public ResponseEntity<?> registerCook(@RequestBody CookRegisterDTO cookDTO) {
		
		return new ResponseEntity<>(userService.addCook(cookDTO), HttpStatus.CREATED);
	}
}
