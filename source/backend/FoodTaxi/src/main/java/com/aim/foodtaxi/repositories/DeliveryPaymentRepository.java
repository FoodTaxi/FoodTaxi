package com.aim.foodtaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.DeliveryPaymentEntity;

public interface DeliveryPaymentRepository extends JpaRepository<DeliveryPaymentEntity, Long> {

}
