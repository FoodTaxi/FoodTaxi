package com.aim.foodtaxi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

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
public class SchedulerService {

	@Autowired
	Scheduler scheduler;
	
	public void scheduleBidExpiration(long bidId) throws SchedulerException{
		String name = UUID.randomUUID().toString();
		String group = BidExpiryJob.class.getSimpleName()+"_"+bidId;
		
		JobDetail jd = JobBuilder.newJob(BidExpiryJob.class).storeDurably().usingJobData(JobDataKeys.BID_ID.value(), Long.toString(bidId)).withIdentity(name, group).build();
		//sample implementation
		LocalDateTime fiveMinutesLater = LocalDateTime.now().plusMinutes(1);
		Trigger t = TriggerBuilder.newTrigger().forJob(jd).startAt(Date.from(fiveMinutesLater.atZone(ZoneId.systemDefault()).toInstant())).build();
		
		scheduler.scheduleJob(jd, t);
	}
	
	
	public enum JobDataKeys{
		
		BID_ID("bidId");
		
		private String value;
		
		private JobDataKeys(String value){
			this.value = value;
		}
		
		public String value(){
			return this.value;
		}
	}
}
