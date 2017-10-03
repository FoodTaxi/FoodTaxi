package com.aim.foodtaxi.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.aim.foodtaxi.services.BidService;

public class BidExpiryJob implements Job{

	@Autowired
	BidService bidService;
	
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		//TODO Sample implementations
		long id = Long.parseLong((String)ctx.getJobDetail().getJobDataMap().get("bidId"));
	}

}
