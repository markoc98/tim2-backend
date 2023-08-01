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

	@Override
	public Set<Recipe> getRecipes(Long userId) {
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
