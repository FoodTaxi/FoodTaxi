package com.aim.foodtaxi.mappers;

import java.util.List;

import javax.inject.Named;

import org.mapstruct.AfterMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.domain.HistoryDeliveryEntity;
import com.aim.foodtaxi.dto.Delivery;

@Mapper(componentModel = "spring", uses = {})
public abstract class DeliveryMapper {

	@Named("deliveryEntityToDelivery")
	@Mappings({ 
		@Mapping(source = "bestBid.price", target = "bestBidAmount"),
		@Mapping(source = "bestBid.driver.id", target = "bestBidDriverId") 
	})
	public abstract Delivery deliveryEntityToDelivery(DeliveryEntity deliveryEntity);

	@AfterMapping
	public void calledWithSourceAndTarget(DeliveryEntity entity, @MappingTarget Delivery delivery) {
	    if (entity.getBestBid() != null) {
	        delivery.setBestBidDriverName(entity.getBestBid().getDriver().getFirstName() +  " " + entity.getBestBid().getDriver().getLastName());
	    }
	}
	
	@IterableMapping(qualifiedByName = "deliveryEntityToDelivery")
	public abstract List<Delivery> deliveryEntitiesToDeliveries(List<DeliveryEntity> entities);
	
	@Mappings({
		@Mapping(target = "deliveryPayments", ignore=true)
	})
	public abstract HistoryDeliveryEntity deliveryEntityToHistoryDeliveryEntity(DeliveryEntity entity);
}
