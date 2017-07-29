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

    @RequestMapping(value = "/driver/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginDriver(@RequestBody(required = true) AccountCredentials credentials) {

        if (driverService.authenticate(credentials.getUsername(), credentials.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    @RequestMapping(value = "/driver/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginDriver(@RequestBody(required = true) Driver driver) {
        driverService.createDriver(driver);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
