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
	
	@Query("select * from T_DELIVERY dl join T_BID b on  dl.BEST_BID_ID = b.ID where b.DRIVER_ID = :driverId")
	public List<DeliveryEntity> getOpenDeliveriesByDriverWinningBids(@Param("driverId")Long driverId);
	
	public List<DeliveryEntity> getAllDeliveryByStatus(DeliveryStatus status);
}
