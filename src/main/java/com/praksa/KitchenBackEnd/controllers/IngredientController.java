package com.praksa.KitchenBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.KitchenBackEnd.controllers.util.RESTError;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.services.IngredientServiceImpl;

@RestController
@RequestMapping(path="/api/v1/project/ingredient")
public class IngredientController {

	@Autowired
	private IngredientServiceImpl ingredientServiceImpl;

	
	@RequestMapping(method = RequestMethod.POST, value = "/newIngredient")
	public ResponseEntity<?> addNewIngredient(@RequestBody Ingredient ingredients) {
		try { 
			ingredientServiceImpl.addIngredient(ingredients);
			return new ResponseEntity<>(ingredients, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<RESTError>(
					new RESTError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"BAD Request"), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateIngredient/{id}")
	public ResponseEntity<?> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredientForUpdate) {
	    try {
	        Ingredient updatedIngredient = ingredientServiceImpl.updateIngredient(ingredientForUpdate, id);
	        if (updatedIngredient != null) {
	            return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(new RESTError(HttpStatus.NOT_FOUND.value(), "Ingredient not found"), HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(new RESTError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getIngredientById(@PathVariable Long id) {
	    try {
	        Ingredient getIngredientById = ingredientServiceImpl.getIngredientById(id);
	        if (getIngredientById != null) {
	            return new ResponseEntity<>(getIngredientById, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(new RESTError(HttpStatus.NOT_FOUND.value(), "Ingredient not found"), HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(new RESTError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allIngredients")
	public ResponseEntity<?> getAllIngredients() {
	    try {
	        Iterable<Ingredient> getAllIngredients = ingredientServiceImpl.getAllIngredients();
	        if (getAllIngredients != null) {
	            return new ResponseEntity<>(getAllIngredients, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(new RESTError(HttpStatus.NOT_FOUND.value(), "Ingredient not found"), HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(new RESTError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteIngredient/{id}")
	public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
	    Ingredient deletedIngredient = ingredientServiceImpl.deleteIngredient(id);
	    if (deletedIngredient != null) {
	        return new ResponseEntity<>("Ingredient is deleted", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(new RESTError(HttpStatus.NOT_FOUND.value(), "Ingredient not found"), HttpStatus.NOT_FOUND);
	    }
}	
	
}
