package com.aim.foodtaxi.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "/open", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<List<Delivery>> getOpenDeliveries(@RequestHeader(value = "authorization") String authString, @ApiIgnore Principal principal){
		try{
			Driver driver = driverService.getDriverByUsername(principal.getName());
			List<Delivery> resp = deliveryService.getOpenDeliveriesByDriver(driver.getId());
			return new ResponseEntity<List<Delivery>>(resp, HttpStatus.OK);
		} catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
