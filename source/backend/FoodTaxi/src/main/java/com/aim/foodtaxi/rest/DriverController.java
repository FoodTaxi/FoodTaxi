package com.aim.foodtaxi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.services.DriverService;

@RestController
@RequestMapping("/api/private/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

	

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Driver getDriver(@RequestHeader(value = "authorization") String authString,
            @RequestParam(name="dId", required=true) Long driverId) {
    	Driver driver = driverService.getDriver(driverId);
        return driver;
    }
}
