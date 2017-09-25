package com.aim.foodtaxi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.domain.DeliveryPaymentEntity;
import com.aim.foodtaxi.domain.HistoryBidEntity;
import com.aim.foodtaxi.domain.HistoryDeliveryEntity;
import com.aim.foodtaxi.mappers.BidMapper;
import com.aim.foodtaxi.mappers.DeliveryMapper;
import com.aim.foodtaxi.repositories.BidRepository;
import com.aim.foodtaxi.repositories.DeliveryPaymentRepository;
import com.aim.foodtaxi.repositories.DeliveryRepository;
import com.aim.foodtaxi.repositories.HistoryBidRepository;
import com.aim.foodtaxi.repositories.HistoryDeliveryRepository;

@Service
@Transactional(readOnly = true)
public class HistoryService {

	@Autowired
	private DeliveryRepository deliveryRepo;

	@Autowired
	private BidRepository bidRepo;

	@Autowired
	private HistoryDeliveryRepository historyDeliveryRepo;

	@Autowired
	private HistoryBidRepository historyBidRepo;

	@Autowired
	private DeliveryPaymentRepository deliveryPaymentRepo;

	@Autowired
	private DeliveryMapper deliveryMapper;

	@Autowired
	private BidMapper bidMapper;

	@Transactional(readOnly = false)
	public void archiveDelivery(long deliveryId) {
		// get delivery
		DeliveryEntity delivery = deliveryRepo.getOne(deliveryId);

		// create & save history delivery
		HistoryDeliveryEntity h_delivery = deliveryMapper.deliveryEntityToHistoryDeliveryEntity(delivery);
		historyDeliveryRepo.save(h_delivery);

		// create & save history bids
		// custom map: isWinning, history delivery
		List<HistoryBidEntity> h_bidEntities = new ArrayList<>();
		BidEntity winBid = delivery.getBestBid();
		List<BidEntity> bids = bidRepo.getByDelivery(delivery);
		for (BidEntity bid : bids) {
			HistoryBidEntity h_bid = bidMapper.bidEntityToHistoryBidEntity(bid);
			h_bid.setDelivery(h_delivery);
			if (winBid != null && bid.getId().equals(winBid.getId())) {
				h_bid.setWinning(true);
			}
		}
		historyBidRepo.save(h_bidEntities);

		// get delivery payments and switch to them to point to history
		// deliveries
		List<DeliveryPaymentEntity> delPayments = delivery.getDeliveryPayments();
		for (DeliveryPaymentEntity delPayment : delPayments) {
			delPayment.setHistoryDelivery(h_delivery);
			delPayment.setDelivery(null);
		}
		deliveryPaymentRepo.save(delPayments);
		
		// delete bids and delivery
		bidRepo.delete(bids);
		deliveryRepo.delete(delivery);
	}
}
