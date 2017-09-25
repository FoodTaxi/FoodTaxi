package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.aim.foodtaxi.domain.BidEntity;
import com.aim.foodtaxi.domain.HistoryBidEntity;
import com.aim.foodtaxi.dto.Bid;

@Mapper(componentModel = "spring", uses = {})
public interface BidMapper {

    public BidEntity bidToBidEntity(Bid bid);
    
    @Mappings({
    	@Mapping(target = "winning", ignore = true),
    	@Mapping(target = "delivery", ignore = true)
    })
    public HistoryBidEntity bidEntityToHistoryBidEntity(BidEntity entity);
    
    

}
