package com.aim.foodtaxi.rest;

import java.security.Principal;

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

import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.services.BidService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class BidController {
    
	@Autowired
    private BidService bidService;

    @RequestMapping(value = "/bid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBid(@RequestHeader(value = "authorization") String authString,
            @RequestParam Long orderId, @RequestBody Bid bid, @ApiIgnore Principal principal) {
    	
    	try{
    		if (bidService.createBid(bid, orderId, principal.getName())) {
    			return new ResponseEntity<>(null, HttpStatus.CREATED);
    		} else{
    			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

}
