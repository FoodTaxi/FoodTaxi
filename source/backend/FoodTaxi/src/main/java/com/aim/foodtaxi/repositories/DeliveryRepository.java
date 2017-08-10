package com.aim.foodtaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.DeliveryEntity;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

}
