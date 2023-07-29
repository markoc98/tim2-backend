package com.praksa.KitchenBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.KitchenBackEnd.services.IngredientService;

@RestController
public class IngredientController {

	@Autowired
	IngredientService ingService;
	
	
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteIngredient/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
		return new ResponseEntity<>(ingService.deleteIngredient(id), HttpStatus.ACCEPTED);
	}
	
}
