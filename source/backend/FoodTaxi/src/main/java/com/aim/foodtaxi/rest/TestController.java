package com.aim.foodtaxi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.services.Scheduler1;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private Scheduler1 s;
	
	@RequestMapping(path="/schedule", method = RequestMethod.GET)
	public void schedule(){
		try {
			s.scheduleBidExpiration(4);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
