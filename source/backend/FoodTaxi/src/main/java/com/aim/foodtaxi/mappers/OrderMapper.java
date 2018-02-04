package com.aim.foodtaxi.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.dto.Order;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
	
	@Named("orderEntityToOrder")
	@Mappings({ 
		@Mapping(source = "brand.id", target = "brandId"),
		@Mapping(source = "client.id", target = "clientId"),
		@Mapping(source = "shop.id", target = "shopId"),
		@Mapping(source = "shop.addressText", target = "shopAddressText"),
		@Mapping(source = "delivery.status", target = "deliveryStatus"),
		@Mapping(source = "delivery.pin", target = "pin"),
		@Mapping(source = "customerName", target = "custName"),
		@Mapping(source = "customerPhone", target = "custPhone"),
		@Mapping(source = "customerEmail", target = "custEmail")
	})
	public Order orderEntityToOrder(OrderEntity orderEntity);

	
	@IterableMapping(qualifiedByName = "orderEntityToOrder")
	public List<Order> orderEntitiesToOrders(List<OrderEntity> orderEntities);

	// @IterableMapping(qualifiedByName = "orderToOrderEntity")
	// public List<OrderEntity> ordersToOrderEntities(List<Order> orders);
	//
	// @Mapping(target = "driver", ignore = true)
	// public OrderEntity unknownOrderToOrderEntity(UnknownOrder unknownOrder);
}
