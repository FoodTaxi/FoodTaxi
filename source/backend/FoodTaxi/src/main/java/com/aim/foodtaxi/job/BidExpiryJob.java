package com.aim.foodtaxi.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.aim.foodtaxi.services.TestService;

public class BidExpiryJob implements Job{

	@Autowired
	TestService service;
	
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		long id = Long.parseLong((String)ctx.getJobDetail().getJobDataMap().get("bidId"));
		service.doSomething();
	}

}
