package com.aim.foodtaxi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.ShopEntity;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

//    Optional<ShopEntity> findOneById(Long shopId);
//
//    Optional<ShopEntity> findFirstByBrandIdAndLatitudeAndLongtitude(Long brandId, Double latitude,
//            Double longtitude);
}
