package com.aim.foodtaxi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.DriverEntity;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

//	List<DriverEntity> findByLastName(String lastName);
//
//	Optional<DriverEntity> findOneById(Long driverId);

	Optional<DriverEntity> findOneByUsername(String username);
	
	DriverEntity findOne(Long id);

	Optional<DriverEntity> findOneByUsernameAndPassword(String username, String password);
}
