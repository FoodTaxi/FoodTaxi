package com.aim.foodtaxi.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.CompleteDelivery;
import com.aim.foodtaxi.dto.Delivery;
import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.services.DeliveryService;
import com.aim.foodtaxi.services.DriverService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/private/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private DriverService driverService;

	@PreAuthorize("hasAuthority('driver')")
	@RequestMapping(value = "/driver", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Delivery>> getDeliveries(@RequestHeader(value = "authorization") String authString,
			@ApiIgnore Principal principal) {
		try {
			Driver driver = driverService.getDriverByUsername(principal.getName());
			List<Delivery> resp = deliveryService.getDeliveriesByDriver(driver.getId());
			return new ResponseEntity<List<Delivery>>(resp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('driver')")
	@RequestMapping(value = "/complete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> completeDelivery(@RequestHeader(value = "authorization") String authString,
			@ApiIgnore Principal principal, @RequestBody CompleteDelivery request) {
		deliveryService.completeDelivery(request.getDeliveryId(), request.getDelivered(), request.getDenialReason());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
