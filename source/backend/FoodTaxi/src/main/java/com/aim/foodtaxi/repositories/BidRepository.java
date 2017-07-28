package com.aim.foodtaxi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.BidEntity;

public interface BidRepository extends JpaRepository<BidEntity, Long> {

//    Optional<BidEntity> findOneById(Long bidId);
}
