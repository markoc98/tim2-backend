package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.entities.CookEntity;

public interface CookRepository extends CrudRepository<CookEntity, Long> {

}
