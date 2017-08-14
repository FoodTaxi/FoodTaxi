package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.mappers.BidMapper;
import com.aim.foodtaxi.repositories.BidRepository;
import com.aim.foodtaxi.repositories.DeliveryRepository;
import com.aim.foodtaxi.repositories.DriverRepository;

@Service
@Transactional(readOnly=true)
public class BidService {
	
    @Autowired
    private BidRepository bidRepository;
    
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private BidMapper bidMapper;

    @Transactional(readOnly=false)
    public void createBid(Bid bid, String driverUsername) {
        BidEntity bidEntity = bidMapper.bidToBidEntity(bid);
        Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findOneById(bid.getDeliveryId());
        Optional<DriverEntity> driverEntity = driverRepository.findOneByUsername(driverUsername);
        if (!driverEntity.isPresent() || !deliveryEntity.isPresent()) {
        	throw new EntityNotFoundException("Driver and/or delivery do not exists!");
        }
        
        bidEntity.setDriver(driverEntity.get());
        bidEntity.setDelivery(deliveryEntity.get());


        BidEntity savedEntity = bidRepository.save(bidEntity);
        if (deliveryEntity.get().getBestBid() == null || 
                deliveryEntity.get().getBestBid().getPrice() > bidEntity.getPrice()) {
            deliveryEntity.get().setBestBid(savedEntity);
            deliveryRepository.save(deliveryEntity.get());
        }
    }
}
