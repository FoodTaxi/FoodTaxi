package com.aim.foodtaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.HistoryDeliveryEntity;

public interface HistoryDeliveryRepository extends JpaRepository<HistoryDeliveryEntity, Long> {

	
}
