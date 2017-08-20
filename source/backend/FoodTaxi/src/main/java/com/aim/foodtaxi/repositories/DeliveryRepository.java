package com.aim.foodtaxi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.enums.DeliveryStatus;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

	public Optional<DeliveryEntity> findOneById(Long id);
	
	@Query(value = "select dl from DeliveryEntity dl inner join dl.bestBid b inner join b.driver d where d.id = :driverId")
	public List<DeliveryEntity> getOpenDeliveriesByDriverWinningBids(@Param("driverId")Long driverId);
	
	public List<DeliveryEntity> getAllDeliveryByStatus(DeliveryStatus status);
}
