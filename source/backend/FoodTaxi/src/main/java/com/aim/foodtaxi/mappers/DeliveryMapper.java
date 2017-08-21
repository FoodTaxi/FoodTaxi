package com.aim.foodtaxi.mappers;

import java.util.List;

import javax.inject.Named;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.dto.Delivery;

@Mapper(componentModel = "spring", uses = {})
public interface DeliveryMapper {

	@Named("deliveryEntityToDelivery")
	@Mappings({ 
		@Mapping(source = "bestBid.price", target = "bestBidAmount"),
		@Mapping(source = "bestBid.driver.id", target = "bestBidDriverId") 
	})
	public Delivery deliveryEntityToDelivery(DeliveryEntity deliveryEntity);

	@IterableMapping(qualifiedByName = "deliveryEntityToDelivery")
	public List<Delivery> deliveryEntitiesToDeliveries(List<DeliveryEntity> entities);
}
