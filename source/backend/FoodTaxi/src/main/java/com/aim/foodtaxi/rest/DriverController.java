package com.aim.foodtaxi.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.services.DriverService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/private/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getDriver(@RequestHeader(value = "authorization") String authString,
			@ApiIgnore Principal principal) {
		String username = principal.getName();
		if (username == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Driver driver = driverService.getDriverByUsername(username);
		if (driver != null) {
			return new ResponseEntity<Driver>(driver, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<?> updateLocation(@RequestHeader(value = "authorization") String authString,
			@ApiIgnore Principal principal, @RequestParam(name = "lat", required = true) double lat,
			@RequestParam(name = "lng", required = true) double lng) {

		return null;
	}
}
