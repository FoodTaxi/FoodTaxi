package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.dto.Bid;

@Mapper(componentModel = "spring", uses = {})
public interface BidMapper {
    
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "order.id", target = "orderId")
    public Bid bidEntityToBid(BidEntity bidEntity);
   
    public BidEntity bidToBidEntity(Bid bid);
    
    
}
