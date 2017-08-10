package com.aim.foodtaxi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aim.foodtaxi.domain.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findOneById(Long clientId);

}
