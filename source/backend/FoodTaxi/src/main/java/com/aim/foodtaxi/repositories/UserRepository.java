package com.aim.foodtaxi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aim.foodtaxi.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	List<UserEntity> findByLastName(String lastName);
}
