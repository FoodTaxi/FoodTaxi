package com.aim.foodtaxi.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Order;
import com.aim.foodtaxi.services.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Inject
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/openOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOpenOrders() {
        List<Order> ordersWithoutDrivers = orderService.getOrdersWithoutDrivers();
        return new ResponseEntity<>(ordersWithoutDrivers, HttpStatus.OK);
    }
}
