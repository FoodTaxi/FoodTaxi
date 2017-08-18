package com.aim.foodtaxi.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.dto.Delivery;
import com.aim.foodtaxi.enums.DeliveryStatus;
import com.aim.foodtaxi.mappers.DeliveryMapper;
import com.aim.foodtaxi.repositories.DeliveryRepository;

@Service
@Transactional(readOnly=false)
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepo;
	
	@Autowired
	private DeliveryMapper deliveryMapper;
	
	public List<Delivery> getOpenDeliveriesByDriver(Long driverId){
		List<Delivery> resp;
		
		List<DeliveryEntity> winningDelivery = deliveryRepo.getOpenDeliveriesByDriverWinningBids(driverId);
		if(winningDelivery != null && !winningDelivery.isEmpty()){
			//initialize best bid
			winningDelivery.forEach(x -> x.getBestBid().getId());
			resp = winningDelivery.stream().map(x -> deliveryMapper.deliveryEntityToDelivery(x)).collect(Collectors.toList());
		}else{
			List<DeliveryEntity> deliveries = deliveryRepo.getAllDeliveryByStatus(DeliveryStatus.BIDDING);
			//initialize best bid
			deliveries.forEach(x -> x.getBestBid().getId());
			resp = deliveries.stream().map(x -> deliveryMapper.deliveryEntityToDelivery(x)).collect(Collectors.toList());
		}
		
		return resp;
	}
}
