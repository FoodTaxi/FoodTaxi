package com.aim.foodtaxi.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.dto.Order;
import com.aim.foodtaxi.mappers.OrderMapper;
import com.aim.foodtaxi.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderMapper orderMapper;

    public void createOrder(Order order) {
        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        orderRepository.save(orderEntity);
    }

    public List<Order> getOrdersWithoutDrivers() {
        List<OrderEntity> orders = orderRepository.findAllByDriverIsNull();
        return orderMapper.orderEntitiesToOrders(orders);
    }
}
