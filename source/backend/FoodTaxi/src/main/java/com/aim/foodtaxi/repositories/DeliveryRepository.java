package com.aim.foodtaxi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.DeliveryEntity;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

	public Optional<DeliveryEntity> findOneById(Long id);
}
