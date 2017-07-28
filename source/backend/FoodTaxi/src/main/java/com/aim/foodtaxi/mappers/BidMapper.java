package com.aim.foodtaxi.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.dto.Bid;

@Mapper(componentModel = "spring", uses = {})
public abstract class BidMapper {

//    @Mapping(source = "driver.id", target = "driverId")
//    @Mapping(source = "order.id", target = "orderId")
//    public abstract Bid bidEntityToBid(BidEntity bidEntity);
//
//    public abstract BidEntity bidToBidEntity(Bid bid);
//
//    @AfterMapping
//    public void calledWithSourceAndTargetType(BidEntity bidEntity, @MappingTarget Bid targetBid) {
//        targetBid.setDriverFullName(bidEntity.getDriver().getFirstName() + " " + bidEntity.getDriver().getLastName());
//    }

}
