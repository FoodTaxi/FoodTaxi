package com.aim.foodtaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.DeliveryEntity;

public interface DelivaryRepository extends JpaRepository<DeliveryEntity, Long> {

}
