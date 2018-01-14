package com.aim.foodtaxi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.dto.Delivery;
import com.aim.foodtaxi.mappers.BidMapper;
import com.aim.foodtaxi.mappers.DeliveryMapper;
import com.aim.foodtaxi.repositories.BidRepository;
import com.aim.foodtaxi.repositories.DeliveryRepository;
import com.aim.foodtaxi.repositories.DriverRepository;

@Service
@Transactional(readOnly = true)
public class BidService {

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private DeliveryMapper deliveryMapper;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private BidMapper bidMapper;

	@Transactional(readOnly = false)
	public Delivery createBid(Bid bid, String driverUsername) {
		BidEntity bidEntity = bidMapper.bidToBidEntity(bid);
		Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findOneById(bid.getDeliveryId());
		Optional<DriverEntity> driverEntity = driverRepository.findOneByUsername(driverUsername);
		if (!driverEntity.isPresent() || !deliveryEntity.isPresent()) {
			throw new EntityNotFoundException("Driver and/or delivery do not exists!");
		}
		DeliveryEntity savedDelivery = deliveryEntity.get();

		// check double bidding
		List<DeliveryEntity> winningDelivery = deliveryRepository.getOpenDeliveriesByDriverWinningBids(driverEntity.get().getId());
		if (winningDelivery != null && winningDelivery.size() > 0) {
			return deliveryMapper.deliveryEntityToDelivery(savedDelivery);
		}

		bidEntity.setDriver(driverEntity.get());
		bidEntity.setDelivery(savedDelivery);
		bidEntity.setActive(true);
		bidEntity.setBidTime(new Date());

		BidEntity savedEntity = bidRepository.save(bidEntity);
		if (savedDelivery.getBestBid() == null || savedDelivery.getBestBid().getPrice() > bidEntity.getPrice()) {
			savedDelivery.setBestBid(savedEntity);
			savedDelivery = deliveryRepository.save(savedDelivery);
		}

		return deliveryMapper.deliveryEntityToDelivery(savedDelivery);
	}
}
