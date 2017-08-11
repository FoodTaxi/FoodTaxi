package com.aim.foodtaxi.services;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.domain.ClientEntity;
import com.aim.foodtaxi.domain.DeliveryEntity;
import com.aim.foodtaxi.domain.OrderEntity;
import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.CreateOrder;
import com.aim.foodtaxi.enums.DeliveryStatus;
import com.aim.foodtaxi.enums.OrderStatus;
import com.aim.foodtaxi.repositories.BrandRepository;
import com.aim.foodtaxi.repositories.ClientRepository;
import com.aim.foodtaxi.repositories.DeliveryRepository;
import com.aim.foodtaxi.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private OrderMapper orderMapper;
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private DeliveryRepository delivaryRepository;
    
    public void createOrder(CreateOrder order) throws EntityNotFoundException {
        
        Optional<ClientEntity> clientEntity = clientRepository.findOneById(order.getClientId());
        if (!clientEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        Optional<BrandEntity> brandEntity = brandRepository.findOneById(order.getBrandId());
        if (!brandEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        OrderEntity orderEntity = generateOrder(order, clientEntity.get(), brandEntity.get());
        orderEntity = orderRepository.save(orderEntity);
        DeliveryEntity deliveryEntity = generateDelivery(order, orderEntity);
        deliveryEntity = delivaryRepository.save(deliveryEntity);
    }
    
    private OrderEntity generateOrder (CreateOrder order, ClientEntity clientEntity, BrandEntity brandEntity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setItemDescription(order.getItemDescription());
        orderEntity.setClient(clientEntity);
        orderEntity.setBrand(brandEntity);
        orderEntity.setOrderDate(new Date());
        orderEntity.setStatus(OrderStatus.AWAITING_CONFIRMATION);
        orderEntity.setOrderValue(order.getOrderValue());
        orderEntity.setShop(findBestShop(order, brandEntity));
        return orderEntity;
    }
    private DeliveryEntity generateDelivery(CreateOrder order, OrderEntity orderEntity) {
        DeliveryEntity delivery = new DeliveryEntity();
        delivery.setCodAmount(order.getCodAmount());
        delivery.setDescription(order.getItemDescription());
        delivery.setDueDate(order.getDueDate());
        delivery.setEndAddressText(order.getAddress());
        delivery.setEndLatitude(order.getLatitude());
        delivery.setEndLongtitude(order.getLongtitude());
        delivery.setHasCod(order.isHasCod());
        delivery.setOrder(orderEntity);
        delivery.setOrderValue(order.getOrderValue());
        delivery.setPin(generatePin());
        delivery.setStartAddressText(orderEntity.getShop().getAddressText());
        delivery.setStartLatitude(orderEntity.getShop().getLatitude());
        delivery.setStartLongtitude(orderEntity.getShop().getLongtitude());
        delivery.setStatus(DeliveryStatus.NEW);
        return delivery;
    }
    
    private String generatePin() {
        int minimum = 1000;
        Random randomGenerator = new Random();
        int nextInt = randomGenerator.nextInt(8999);
        return String.valueOf(minimum + nextInt);
    }

    // This method returns the shop which is closest to the order per brand
    private ShopEntity findBestShop(CreateOrder order, BrandEntity brand) {
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

//    public List<Order> getOrdersWithoutDrivers() {
//        List<OrderEntity> orders = orderRepository.findAllByDriverIsNull();
//        return orderMapper.orderEntitiesToOrders(orders);
//    }
//
//    public HttpStatus createUnknownOrder(UnknownOrder order) {
//        Optional<ShopEntity> fakeShop = shopRepository.findFirstByBrandIdAndLatitudeAndLongtitude((long) 0, order.getFromLatitude(), order.getFromLongtitude());
//        ShopEntity shopEntity = null;
//        if (!fakeShop.isPresent()) {
//           shopEntity = new ShopEntity();
//           shopEntity.setAddressText(order.getFromAddressText());
//           shopEntity.setLatitude(order.getFromLatitude());
//           shopEntity.setLongtitude(order.getFromLongtitude());
//           shopEntity.setName("Unknown");
//           shopRepository.save(shopEntity);
//        } else {
//            shopEntity = fakeShop.get();
//        }
//        OrderEntity orderEntity = orderMapper.unknownOrderToOrderEntity(order);
//        orderEntity.setShop(shopEntity);
//        orderRepository.save(orderEntity);
//        return HttpStatus.CREATED;
//    }
}
