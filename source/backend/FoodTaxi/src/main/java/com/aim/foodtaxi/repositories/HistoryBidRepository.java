package com.aim.foodtaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.HistoryBidEntity;

public interface HistoryBidRepository extends JpaRepository<HistoryBidEntity, Long> {

}
