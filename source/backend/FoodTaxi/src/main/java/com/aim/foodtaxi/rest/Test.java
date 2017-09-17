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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Bid;
import com.aim.foodtaxi.services.HistoryService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/private/test")
public class Test {

	@Autowired
	private HistoryService history;

	@RequestMapping(value = "/archieve", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createBid(@RequestHeader(value = "authorization") String authString, @RequestParam Long id,
			@ApiIgnore Principal principal) {

		try {
			history.archiveDelivery(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch(EntityNotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
