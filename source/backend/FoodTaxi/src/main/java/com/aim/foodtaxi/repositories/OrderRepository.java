package com.aim.foodtaxi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> getAllByShopAndStatus(ShopEntity shop, OrderStatus status);
	
	List<OrderEntity> getAllByShopAndStatusIn(ShopEntity shop, List<OrderStatus> status);
}
