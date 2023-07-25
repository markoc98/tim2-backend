package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.entities.RegularUserEntity;

public interface RegularUserRepository extends CrudRepository<RegularUserEntity, Long> {

}
