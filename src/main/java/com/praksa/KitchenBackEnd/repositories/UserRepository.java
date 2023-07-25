package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
