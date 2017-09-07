package com.aim.foodtaxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.domain.DeliveryEntity;

public interface BidRepository extends JpaRepository<BidEntity, Long> {

//    Optional<BidEntity> findOneById(Long bidId);
	
	List<BidEntity> getByDelivery(DeliveryEntity entity);
}
