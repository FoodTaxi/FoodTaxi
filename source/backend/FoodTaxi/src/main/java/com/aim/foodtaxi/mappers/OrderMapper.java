package com.aim.foodtaxi.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.dto.Order;

@Mapper(componentModel = "spring", uses = {DriverMapper.class, BidMapper.class, }, unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface OrderMapper {
   
    @Mapping(source = "brand.id", target = "brandId")
    public Order orderEntityToOrder(OrderEntity orderEntity);
    
    @Mapping(target = "driver", ignore = true)
    public OrderEntity orderToOrderEntity(Order order);
    
    @IterableMapping(qualifiedByName = "orderEntityToOrder")
    public List<Order> orderEntitiesToOrders(List<OrderEntity> orderEntities);
    
    @IterableMapping(qualifiedByName = "orderToOrderEntity")
    public List<OrderEntity> ordersToOrderEntities(List<Order> orders);
}
