package com.aim.foodtaxi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.ShopUserEntity;

public interface ShopUserRepository extends JpaRepository<ShopUserEntity, Long>{

	Optional<ShopUserEntity> findOneByUsername(String username);
}
