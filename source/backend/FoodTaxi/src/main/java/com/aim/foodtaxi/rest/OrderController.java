package com.aim.foodtaxi.rest;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.ConfirmOrder;
import com.aim.foodtaxi.dto.CreateOrder;
import com.aim.foodtaxi.dto.Order;
import com.aim.foodtaxi.services.OrderService;

import springfox.documentation.annotations.ApiIgnore;

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

	@PreAuthorize("hasAuthority('shop')")
	@RequestMapping(value = "/shop", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrdersForShop(@RequestHeader(value = "authorization") String authString,
			@RequestParam("sid") long shopId) {
		List<Order> respList = null;
		try {
			respList = orderService.getShopOrders(shopId);
			return new ResponseEntity<List<Order>>(respList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('shop')")
	@RequestMapping(value = "/confirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> confirmOrder(@RequestHeader(value = "authorization") String authString,
			@RequestBody ConfirmOrder confirmOrderInput) {

		try {
			orderService.confirmOrder(confirmOrderInput.getOrderId(), confirmOrderInput.isConfirmed(),
					confirmOrderInput.getCompletionMinutes());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PreAuthorize("hasAuthority('shop')")
	@RequestMapping(value = "/handover", method = RequestMethod.POST)
	public ResponseEntity<?> handOverToDelivery(@RequestHeader(value = "authorization") String authString, @ApiIgnore Principal principal, @RequestParam(name="oid", required=true) long orderId) {
		orderService.handOver(orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
