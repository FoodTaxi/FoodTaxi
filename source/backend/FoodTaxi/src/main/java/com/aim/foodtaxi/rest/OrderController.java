package com.aim.foodtaxi.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.CreateOrder;
import com.aim.foodtaxi.dto.Order;
import com.aim.foodtaxi.services.OrderService;

@RestController
@RequestMapping("/api/private/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrder(@RequestHeader(value = "authorization") String authString,
			@RequestBody CreateOrder createOrder) {
		try {
			orderService.createOrder(createOrder);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrdersForConfirmation(
			@RequestHeader(value = "authorization") String authString, @RequestParam("sid")long shopId) {
		List<Order> respList = null;
		try{
			respList = orderService.getOrdersForConfirmation(shopId);
			return new ResponseEntity<List<Order>>(respList, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
