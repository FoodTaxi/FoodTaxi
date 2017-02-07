package com.aim.foodtaxi.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.Order;
import com.aim.foodtaxi.mappers.OrderMapper;
import com.aim.foodtaxi.repositories.BrandRepository;
import com.aim.foodtaxi.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private BrandRepository brandRepository;

    public HttpStatus createOrder(Order order) {
        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        Optional<BrandEntity> brand = brandRepository.findOneById(order.getBrandId());
        if (brand.isPresent()) {
            orderEntity.setBrand(brand.get());
            orderEntity.setShop(findBestShop(order, brand.get()));
            orderRepository.save(orderEntity);
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // This method returns the shop which is closest to the order per brand
    private ShopEntity findBestShop(Order order, BrandEntity brand) {
        ShopEntity bestShop = null;
        Double currentBestDistance = null;
        for (ShopEntity shop : brand.getShops()) {
            Double latitude = order.getLatitude() - shop.getLatitude();
            latitude = latitude * latitude;
            Double longtitude = order.getLongtitude() - shop.getLongtitude();
            longtitude = longtitude * longtitude;

            if (bestShop == null || currentBestDistance > longtitude + latitude) {
                bestShop = shop;
                currentBestDistance = longtitude + latitude;
            }
        }
        return bestShop;
    }

    public List<Order> getOrdersWithoutDrivers() {
        List<OrderEntity> orders = orderRepository.findAllByDriverIsNull();
        return orderMapper.orderEntitiesToOrders(orders);
    }
}
