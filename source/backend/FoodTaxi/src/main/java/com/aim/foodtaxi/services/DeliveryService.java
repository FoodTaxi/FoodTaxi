package com.aim.foodtaxi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.dto.Delivery;
import com.aim.foodtaxi.enums.DeliveryStatus;
import com.aim.foodtaxi.enums.OrderStatus;
import com.aim.foodtaxi.mappers.DeliveryMapper;
import com.aim.foodtaxi.repositories.DeliveryRepository;
import com.aim.foodtaxi.repositories.OrderRepository;

@Service
@Transactional(readOnly = true)
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepo;

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private DeliveryMapper deliveryMapper;

	public List<Delivery> getDeliveriesByDriver(Long driverId) {
		List<Delivery> resp = new ArrayList<>();

		List<DeliveryEntity> winningDelivery = deliveryRepo.getOpenDeliveriesByDriverWinningBids(driverId);
		if (winningDelivery != null && !winningDelivery.isEmpty()) {
			resp = deliveryMapper.deliveryEntitiesToDeliveries(winningDelivery);
		} else {
			Optional<DeliveryEntity> activeDelivery = deliveryRepo.findOneByDriverIdAndStatusIn(driverId,
					Arrays.asList(DeliveryStatus.PICKING_UP, DeliveryStatus.DELIVERY));
			if(activeDelivery.isPresent()){
				resp.add(deliveryMapper.deliveryEntityToDelivery(activeDelivery.get()));
			}else{
				List<DeliveryEntity> deliveries = deliveryRepo.getAllByStatus(DeliveryStatus.BIDDING);
				if (deliveries != null && !deliveries.isEmpty()) {
					resp = deliveryMapper.deliveryEntitiesToDeliveries(deliveries);
				}
			}
		}
		return resp;
	}

	@Transactional(readOnly = false)
	public void closeBidding(long deliveryId) {
		DeliveryEntity delivery = deliveryRepo.getOne(deliveryId);
		if (delivery.getBestBid() != null) {
			delivery.setStatus(DeliveryStatus.PICKING_UP);
			delivery.setDriver(delivery.getBestBid().getDriver());
		} else {
			delivery.setStatus(DeliveryStatus.ESCALATED_BID);
		}
		deliveryRepo.save(delivery);
	}

	@Transactional(readOnly = false)
	public void completeDelivery(long deliveryId, boolean delivered, String denialReason) {
		DeliveryEntity delivery = deliveryRepo.getOne(deliveryId);
		OrderEntity order = delivery.getOrder();
		if(delivered){
			delivery.setStatus(DeliveryStatus.DELIVERED);
			order.setStatus(OrderStatus.DELIVERED);
		} else{
			delivery.setStatus(DeliveryStatus.NOT_DELIVERED);
			delivery.setDescription(delivery.getDescription() + "Failed delivery reason: " + denialReason);
			order.setStatus(OrderStatus.NOT_DELIVERED);
		}
		deliveryRepo.save(delivery);
		orderRepo.save(order);
	}
}
