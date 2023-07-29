package com.praksa.KitchenBackEnd.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.LimitingIngredient;
import com.praksa.KitchenBackEnd.repositories.IngredientRepository;
import com.praksa.KitchenBackEnd.repositories.LimitingIngredientRepository;


@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	LimitingIngredientRepository limIngredientRepo;
	
	@Override
	public Ingredient deleteIngredient(Long id) {
		
		Ingredient ingredient = ingredientRepository.findById(id).get();
		List<LimitingIngredient> limits = new ArrayList<>();
		for(LimitingIngredient li : limIngredientRepo.findAll()) {
			if(li.getIngredients().equals(ingredient)) {
				limits.add(li);
			}
		}
		if(!limits.isEmpty()) {
		limIngredientRepo.deleteAll(limits);
		}
		
		ingredientRepository.delete(ingredient);
		
		return ingredient;
	}
}
