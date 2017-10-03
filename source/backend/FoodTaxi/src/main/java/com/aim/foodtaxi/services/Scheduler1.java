package com.aim.foodtaxi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aim.foodtaxi.job.BidExpiryJob;

@Service
public class Scheduler1 {

	@Autowired
	Scheduler scheduler;
	
	public void scheduleBidExpiration(long bidId) throws SchedulerException{
		JobDetail jd = JobBuilder.newJob(BidExpiryJob.class).storeDurably().usingJobData("bidId", Long.toString(bidId)).withIdentity(BidExpiryJob.class.getSimpleName()+"_"+bidId).build();
		
		LocalDateTime fiveMinutesLater = LocalDateTime.now().plusMinutes(1);
		Trigger t = TriggerBuilder.newTrigger().forJob(jd).startAt(Date.from(fiveMinutesLater.atZone(ZoneId.systemDefault()).toInstant())).build();
				
		scheduler.scheduleJob(jd, t);
	}
}
