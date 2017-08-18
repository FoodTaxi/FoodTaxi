package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;

import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.dto.Delivery;

@Mapper(componentModel = "spring", uses = {})
public interface DeliveryMapper {

	public Delivery deliveryEntityToDelivery(DeliveryEntity deliveryEntity);
	
}
