package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.repositories.BidRepository;
import com.aim.foodtaxi.repositories.OrderRepository;

@Service
@Transactional
public class BidService {
    @Autowired
    private BidRepository bidRepository;
    
    @Autowired
    private DeliveryRepository orderRepository;

//    @Inject
//    private DriverRepository driverRepository;
//    
//    @Inject
//    private BidMapper bidMapper;
//
    public boolean createBid(Bid bid, Long orderId, String driverUsername) {
        BidEntity bidEntity = bidMapper.bidToBidEntity(bid);
        Optional<OrderEntity> orderEntity = orderRepository.findOneById(orderId);
        Optional<DriverEntity> driverEntity = driverRepository.findOneById(driverId);
        if (!driverEntity.isPresent() || !orderEntity.isPresent()) {
            return false;
        }
        bidEntity.setDriver(driverEntity.get());
        bidEntity.setOrder(orderEntity.get());


        BidEntity savedEntity = bidRepository.save(bidEntity);
        if (orderEntity.get().getBestBid() == null || 
                orderEntity.get().getBestBid().getPrice() > bidEntity.getPrice()) {
            orderEntity.get().setBestBid(savedEntity);
            orderRepository.save(orderEntity.get());
        }
        return true;
    }
}
