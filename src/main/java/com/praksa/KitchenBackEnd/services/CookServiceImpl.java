package com.praksa.KitchenBackEnd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.entities.Cook;
import com.praksa.KitchenBackEnd.repositories.CookRepository;

@Service
public class CookServiceImpl implements CookService {
	
	@Autowired
	private CookRepository cookRepository;

	@Override
	public Cook getCook(Long id) {
		return cookRepository.findById(id).get();
	}

}
