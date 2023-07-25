package com.praksa.KitchenBackEnd.controllers.factory;

import com.praksa.KitchenBackEnd.models.dto.CookRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.RegularUserRegisterDTO;
import com.praksa.KitchenBackEnd.models.dto.UserRegisterDTO;
import com.praksa.KitchenBackEnd.models.entities.AdministratorEntity;
import com.praksa.KitchenBackEnd.models.entities.CookEntity;
import com.praksa.KitchenBackEnd.models.entities.EUserRole;
import com.praksa.KitchenBackEnd.models.entities.RegularUserEntity;
import com.praksa.KitchenBackEnd.models.entities.UserEntity;



public class UserFactory {
	
	public static UserEntity createUser(UserRegisterDTO userDTO) {
		
		if (userDTO.getRole() == EUserRole.ADMINISTRATOR) {
			
			AdministratorEntity admin = new AdministratorEntity();
			
			admin.setUsername(userDTO.getUsername());
			admin.setPassword(userDTO.getPassword());
			admin.setRole(userDTO.getRole());
			
			return admin;
			
		} else if (userDTO.getRole() == EUserRole.REGULARUSER) {
			
			RegularUserRegisterDTO regUserDTO = (RegularUserRegisterDTO) userDTO;
			RegularUserEntity regUser = new RegularUserEntity();
			
			regUser.setFirstName(regUserDTO.getFirstName());
			regUser.setLastName(regUserDTO.getLastName());
			regUser.setUsername(regUserDTO.getUsername());
			regUser.setPassword(regUserDTO.getPassword());
			regUser.setRole(regUserDTO.getRole());
			
			return regUser;
			
		} else {
			
			CookRegisterDTO cookDTO = (CookRegisterDTO) userDTO;
			CookEntity cook = new CookEntity();
			
			cook.setFirstName(cookDTO.getFirstName());
			cook.setLastName(cookDTO.getLastName());
			cook.setUsername(cookDTO.getUsername());
			cook.setPassword(cookDTO.getPassword());
			cook.setRole(cookDTO.getRole());
			
			return cook;
		}
	}
}
