package com.aim.foodtaxi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.DriverEntity;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

	List<DriverEntity> findByLastName(String lastName);

    Optional<DriverEntity> findOneById(Long driverId);
}
