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

import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.services.BidService;

@RestController
@RequestMapping("/api")
public class BidController {
    @Inject
    private BidService bidService;

    @RequestMapping(value = "/bid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBrand(@RequestParam Long orderId, @RequestParam Long driverId, @RequestBody Bid bid) {
        if(bidService.createBid(bid,orderId, driverId)){
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
