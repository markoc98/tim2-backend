package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.AffectedUsers;

public interface AffectedUserRepository extends CrudRepository<AffectedUsers, Long> {
	
	
}
