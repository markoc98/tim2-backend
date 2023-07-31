package com.praksa.KitchenBackEnd.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.models.entities.RegularUser;
import com.praksa.KitchenBackEnd.repositories.LimitingFactorRepository;
import com.praksa.KitchenBackEnd.repositories.RecipeRepository;
import com.praksa.KitchenBackEnd.repositories.RegularUserRepository;
import com.praksa.KitchenBackEnd.repositories.UserRepository;


@Service
public class RegularUserServiceImpl implements RegularUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private LimitingFactorRepository limFactorRepo;
	
	@Autowired
	private RegularUserRepository regUserRepo;

	@Override
	public Set<LimitingFactor> getLimitingFactors(Long id) {
//		RegularUser user = (RegularUser) userRepository.findById(id).get();
//		return user.getLimitingFactor();
		return null;
	}

	@Override
	public LimitingFactor addLimitingFactor(Long userId, Long lfId) {
//		RegularUser user = (RegularUser) userRepository.findById(userId).get();
//		LimitingFactor lf = limFactorRepo.findById(lfId).get();
//		user.getLimitingFactor().add(lf);
//		userRepository.save(user);
//		return lf;
		return null;
	}

	@Override
	public LimitingFactor removeLimitingFactor(Long userId, Long lfId) {
		RegularUser user = (RegularUser) userRepository.findById(userId).get();
		LimitingFactor lf = limFactorRepo.findById(lfId).get();
		user.getLimitingFactor().remove(lf);
		userRepository.save(user);
		return lf;
	}

	@Override
	public Set<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe addRecipe(Long userId, Long recId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe removeRecipe(Long userId, Long recId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
