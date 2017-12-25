package com.aim.foodtaxi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.AccountCredentials;
import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.services.DriverService;

@RestController
@RequestMapping("/api/public")
public class PublicController {

	@Autowired
	private DriverService driverService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> driverLogin(@RequestBody(required = true) AccountCredentials credentials) {
		return null;
	}

	@RequestMapping(value = "/driver/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> driverRegister(@RequestBody(required = true) Driver driver) {
		HttpStatus status = driverService.createDriver(driver);
		return new ResponseEntity<>(status);
	}
}
