package com.aim.foodtaxi.rest;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.services.BidService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/private/bid")
public class BidController {

	@Autowired
	private BidService bidService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createBid(@RequestHeader(value = "authorization") String authString, @RequestBody Bid bid,
			@ApiIgnore Principal principal) {

		try {
			bidService.createBid(bid, principal.getName());
			return new ResponseEntity<>(null, HttpStatus.CREATED);

		} catch(EntityNotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
