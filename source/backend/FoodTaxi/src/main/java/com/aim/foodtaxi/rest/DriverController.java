package com.aim.foodtaxi.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.services.DriverService;

@RestController
@RequestMapping("/api")
public class DriverController {
    
    @Inject
    private DriverService driverService;
    
    @RequestMapping(value = "/driver", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDriver(@RequestBody Driver driver) {
        driverService.createDriver(driver);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/driver", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDriver(@RequestParam Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }
}
