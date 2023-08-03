package com.praksa.KitchenBackEnd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.praksa.KitchenBackEnd.controllers.util.RESTError;
import com.praksa.KitchenBackEnd.models.dto.LimFactorDTO;
import com.praksa.KitchenBackEnd.services.LimitingFactorService;

@RestController
@RequestMapping(path = "/api/v1/project/limitingFactor")
public class LimitingFactorContreoller {
	
	
	@Autowired
	private LimitingFactorService limitingFactorService;
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/newlimitingFactor/{Id}")
	public ResponseEntity<?> addNewLimitingFactor(@Valid @RequestBody LimFactorDTO limDTO,@PathVariable Long Id) {		
		try { 	
			return new ResponseEntity<>(limitingFactorService.addLimitingFactor(limDTO,Id), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<RESTError>(
					new RESTError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"BAD Request"), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
	}
	
	
	
}
