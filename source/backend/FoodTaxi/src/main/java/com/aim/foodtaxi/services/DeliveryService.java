package com.aim.foodtaxi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.dto.Delivery;
import com.aim.foodtaxi.enums.DeliveryStatus;
import com.aim.foodtaxi.mappers.DeliveryMapper;
import com.aim.foodtaxi.repositories.DeliveryRepository;

@Service
@Transactional(readOnly = true)
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepo;

	@Autowired
	private DeliveryMapper deliveryMapper;

	public List<Delivery> getOpenDeliveriesByDriver(Long driverId) {
		List<Delivery> resp = new ArrayList<>();

		List<DeliveryEntity> winningDelivery = deliveryRepo.getOpenDeliveriesByDriverWinningBids(driverId);
		if (winningDelivery != null && !winningDelivery.isEmpty()) {
			resp = deliveryMapper.deliveryEntitiesToDeliveries(winningDelivery);
		} else {
			List<DeliveryEntity> deliveries = deliveryRepo.getAllByStatus(DeliveryStatus.BIDDING);
			if (deliveries != null && !deliveries.isEmpty()) {
				resp = deliveryMapper.deliveryEntitiesToDeliveries(deliveries);
			}
		}
		return resp;
	}

	@Transactional(readOnly=false)
	public void closeBidding(long deliveryId) {
		DeliveryEntity delivery = deliveryRepo.getOne(deliveryId);
		if(delivery.getBestBid().getId() != null) {
			delivery.setStatus(DeliveryStatus.PICKING_UP);
			delivery.setDriver(delivery.getBestBid().getDriver());
		} else {
			delivery.setStatus(DeliveryStatus.ESCALATED_BID);
		}
		deliveryRepo.save(delivery);
	}
}
