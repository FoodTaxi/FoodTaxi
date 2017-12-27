package com.aim.foodtaxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.ShopEntity;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
	
	public List<ShopEntity> getAllByBrandClientId(Long clientId);
}
