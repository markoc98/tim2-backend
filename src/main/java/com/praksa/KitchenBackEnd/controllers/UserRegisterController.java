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

@RestController
@RequestMapping(path = "api/v1/project/register")
public class UserRegisterController {
	
	//TODO: Validacije, hvatanje gresaka, loggovanje?

	@Autowired
	AdministratorRepository adminRepo;
	
	@Autowired
	CookRepository cookRepository;
	
	@Autowired
	RegularUserRepository regUserRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	
	//Proste metode za testiranje, razgranacemo ih blagovremeno
	
	@RequestMapping(method = RequestMethod.POST, value = "/admin")
	public ResponseEntity<?> registerAdmin(@RequestBody AdminRegisterDTO adminDTO) {
		
		Administrator admin = (Administrator) UserFactory.createUser(adminDTO);
		userRepo.save(admin);
		
		return new ResponseEntity<>(admin, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/regUser")
	public ResponseEntity<?> registerRegUser(@RequestBody RegularUserRegisterDTO regUserDTO) {
		
		RegularUser regUser = (RegularUser) UserFactory.createUser(regUserDTO);
		userRepo.save(regUser);
		
		return new ResponseEntity<>(regUser, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/cook")
	public ResponseEntity<?> registerCook(@RequestBody CookRegisterDTO cookDTO) {
		
		Cook cook = (Cook) UserFactory.createUser(cookDTO);
		userRepo.save(cook);
		
		return new ResponseEntity<>(cook, HttpStatus.CREATED);
	}
}
