package com.aim.foodtaxi.rest;

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

import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.services.DriverService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

//    @Autowired
//    private DriverService driverService;
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createDriver(@RequestBody Driver driver) {
//        driverService.createDriver(driver);
//        return new ResponseEntity<>(null, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getDriver(@RequestHeader(value = "authorization") String authString,
//            @RequestParam Long driverId) {
//        Driver driver = driverService.getDriverById(driverId);
//        return new ResponseEntity<>(driver, HttpStatus.OK);
//    }
}
