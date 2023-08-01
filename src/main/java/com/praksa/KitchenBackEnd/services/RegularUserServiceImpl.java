package com.praksa.KitchenBackEnd.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praksa.KitchenBackEnd.models.entities.AffectedUsers;
import com.praksa.KitchenBackEnd.models.entities.LimitingFactor;
import com.praksa.KitchenBackEnd.models.entities.Recipe;
import com.praksa.KitchenBackEnd.models.entities.RegularUser;
import com.praksa.KitchenBackEnd.repositories.AffectedUserRepository;
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
	
	@Autowired
	private AffectedUserRepository affectedUsersRepo;
	
	
	
	
	
	//=========================FOR LIMITING FACTORS=======================//
	
	@Override
	public Set<LimitingFactor> getLimitingFactors(Long userId) {
		RegularUser user = (RegularUser) userRepository.findById(userId).get();
		Set<LimitingFactor> lf = new HashSet<>();
		for(AffectedUsers af : affectedUsersRepo.findAll()) {
			if(user.getId().equals(af.getRegularUser().getId())) {
				lf.add(af.getLimitingFactor());
			}
		}
		
		return lf;
	}

	@Override
	public AffectedUsers addLimitingFactor(Long userId, Long lfId) {
		RegularUser user = (RegularUser) userRepository.findById(userId).get();
		LimitingFactor lf = limFactorRepo.findById(lfId).get();
		AffectedUsers affU = new AffectedUsers(null, user, lf);
		affectedUsersRepo.save(affU);
		return affU;
	}

	@Override
	public AffectedUsers removeLimitingFactor(Long lfId) {
		AffectedUsers af = affectedUsersRepo.findById(lfId).get();
		affectedUsersRepo.delete(af);
		return af;
	}
	
	
	
	//=========================FOR RECIPES=========================//
	
	@Override
	public Set<Recipe> getUserRecipes(Long userId) {
//		RegularUser user = (RegularUser) userRepository.findById(userId).get();
//		Set<Recipe> recipes = recipeRepo.
//				findAllByLikedRecipes(user.getLikedRecipes().getId());
//		
		return null;
	}

	
	//Posto je OneToOne od RegularUsera ka LikedRecipes, svi kljucevi moraju biti unikatni
	//zbog toga moram da copiram recept u tabelu recepata i dajem mu strani kljuc LikedRecipe iliti "cookbook-a"
	//ako neko zna bolje resenje...
	@Override
	public Recipe addRecipeToUser(Long userId, Long recipeId) {
//		RegularUser user = (RegularUser) userRepository.findById(userId).get();
//		Recipe recipe = recipeRepo.findById(recipeId).get();
//		if(recipe.getLikedRecipes() == user.getLikedRecipes()) {
//			return null;
//		}
//		Recipe rec = new Recipe(null, recipe.getTitle(), 
//				recipe.getDescription(), 
//				recipe.getSteps(), recipe.getTimeToPrepare(),
//				recipe.getCreatedOn(), recipe.getUpdatedOn(), 
//				recipe.getAmount(), recipe.getCategory(), 
//				recipe.getVersion(), recipe.getIngredients(), 
//				recipe.getCook(),user.getLikedRecipes());		
//		recipeRepo.save(rec);
//		return recipe;
		return null;
	}

	@Override
	public Recipe removeRecipe(Long userId, Long recId) {
		RegularUser user = (RegularUser) userRepository.findById(userId).get();
		Recipe recipe = recipeRepo.findByLikedRecipes(recId);
		recipeRepo.delete(recipe);
		return null;
	}

	
	
	
	
}
