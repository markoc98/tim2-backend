package com.praksa.KitchenBackEnd.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Override
	public Ingredient addIngredient(Ingredient ingredient) {
		Ingredient ingredients = new Ingredient();
		ingredients.setCalories(ingredient.getCalories());
		ingredients.setCarbs(ingredient.getCarbs());
		ingredients.setFats(ingredient.getFats());
		ingredients.setName(ingredient.getName());
		ingredients.setProteins(ingredient.getProteins());
		ingredients.setSaturatedFats(ingredient.getSaturatedFats());
		ingredients.setSugars(ingredient.getSugars());
		ingredients.setUnit(ingredient.getUnit());
		ingredientRepository.save(ingredients);
		return ingredients;
		
	}

	@Override
	public Ingredient updateIngredient(Ingredient ingredientForUpdate, Long id) {
	    Optional<Ingredient> ingredient = ingredientRepository.findById(id);
	    if (ingredient.isPresent()) {
	        Ingredient updateIngredient = ingredient.get();
	        updateIngredient.setCalories(ingredientForUpdate.getCalories());
	        updateIngredient.setCarbs(ingredientForUpdate.getCarbs());
	        updateIngredient.setFats(ingredientForUpdate.getFats());
	        updateIngredient.setName(ingredientForUpdate.getName());
	        updateIngredient.setProteins(ingredientForUpdate.getProteins());
	        updateIngredient.setSaturatedFats(ingredientForUpdate.getSaturatedFats());
	        updateIngredient.setSugars(ingredientForUpdate.getSugars());
	        updateIngredient.setUnit(ingredientForUpdate.getUnit());
	        return ingredientRepository.save(updateIngredient);
	    } else {
	        return null;
	    }
	}
	
    @Override
    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredientById = ingredientRepository.findById(id);
        if(ingredientById.isPresent()) {
        	return ingredientById.get();
        }else {
        	return null;
        }
    }

	@Override
	public Iterable<Ingredient> getAllIngredients() {
		Iterable<Ingredient> allIngredients = ingredientRepository.findAll();	
		if(allIngredients != null) {
			return allIngredients;
		}
		return null;
	}

	@Override
	public Ingredient deleteIngredient(Long id) {
        Optional<Ingredient> deleteIngredient = ingredientRepository.findById(id);
		if(deleteIngredient.isPresent()) {
			 Ingredient deletedIngredient = deleteIngredient.get();
			  ingredientRepository.delete(deletedIngredient);
			  return deletedIngredient; 
		}
		return null;
	}

}
