package com.aim.foodtaxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByLastName(String lastName);
}
