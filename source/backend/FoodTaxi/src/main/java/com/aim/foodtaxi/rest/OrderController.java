package com.aim.foodtaxi.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Order;
import com.aim.foodtaxi.dto.UnknownOrder;
import com.aim.foodtaxi.services.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Inject
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@RequestHeader(value = "authorization") String authString,
            @RequestBody Order order) {
        HttpStatus createOrderStatus = orderService.createOrder(order);
        return new ResponseEntity<>(null, createOrderStatus);
    }

    @RequestMapping(value = "/unknownOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUnknownOrder(@RequestHeader(value = "authorization") String authString,
            @RequestBody UnknownOrder order) {
        HttpStatus createOrderStatus = orderService.createUnknownOrder(order);
        return new ResponseEntity<>(null, createOrderStatus);
    }

    @RequestMapping(value = "/openOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOpenOrders(@RequestHeader(value = "authorization") String authString, @AuthenticationPrincipal User user) {
        List<Order> ordersWithoutDrivers = orderService.getOrdersWithoutDrivers();
        return new ResponseEntity<>(ordersWithoutDrivers, HttpStatus.OK);
    }
}
