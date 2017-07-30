package com.aim.foodtaxi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
