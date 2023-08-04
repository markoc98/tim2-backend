package com.praksa.KitchenBackEnd.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.dto.IngredientDTO;
import com.praksa.KitchenBackEnd.models.entities.Ingredient;
import com.praksa.KitchenBackEnd.models.entities.LimitingIngredient;
import com.praksa.KitchenBackEnd.models.entities.RecipeIngredient;
import com.praksa.KitchenBackEnd.repositories.IngredientRepository;
import com.praksa.KitchenBackEnd.repositories.LimitingIngredientRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeIngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private LimitingIngredientRepository limitingIngredientRepo;
	@Autowired
	private RecipeIngredientRepository recipeIngredientRepository;
	
	
	
	@Override
	public Ingredient addIngredient(IngredientDTO ingredient) {
		Ingredient ingredients = new Ingredient();
		ingredients.setCalories(ingredient.getCalories());
		ingredients.setCarbs(ingredient.getCarbs());
		ingredients.setFats(ingredient.getFats());
		ingredients.setName(ingredient.getName());
		ingredients.setProteins(ingredient.getProteins());
		ingredients.setSaturatedFats(ingredient.getSaturatedFats());
		ingredients.setSugars(ingredient.getSugars());
		ingredients.setUnit(ingredient.getUnit());
		return ingredientRepository.save(ingredients);
		 
		
	}

	@Override
	public Ingredient updateIngredient(IngredientDTO ingredientForUpdate, Long id) {
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

//	@Override
//	public Ingredient deleteIngredient(Long id) {
//        Ingredient deleteIngredient = ingredientRepository.findById(id).get();
//		if(deleteIngredient != null) {
//			  ingredientRepository.delete(deleteIngredient);
//			  return deleteIngredient; 
//		}
//		return null;
//	}

	@Override
	@Transactional
	public Ingredient deleteIngredient(Long id) {
		Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();
		List<LimitingIngredient> limits = ingredient.getLimitingFactors();
		List<RecipeIngredient> recipes = recipeIngredientRepository.findByIngredientId(ingredient);
		recipeIngredientRepository.deleteAll(recipes);
		limitingIngredientRepo.deleteAll(limits);
		ingredientRepository.delete(ingredient);
		return ingredient;
	}
}
