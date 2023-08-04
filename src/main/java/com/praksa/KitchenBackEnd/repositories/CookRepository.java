package com.praksa.KitchenBackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praksa.KitchenBackEnd.models.entities.Cook;

public interface CookRepository extends CrudRepository<Cook, Long> {

}
