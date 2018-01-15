package com.aim.foodtaxi.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.aim.foodtaxi.enums.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Delivery {

	private Long id;
	private String description;
	private Double startLongtitude;
	private Double startLatitude;
	private String startAddressText;
	private Double endLongtitude;
	private Double endLatitude;
	private String endAddressText;
	private Date startDate;
	private Date endDate;
	private Date dueDate;
	private Date pickupDueDate;
	private Double bestBidAmount;
	private Long bestBidDriverId;
	private String bestBidDriverName;
	private DeliveryStatus status;
	private boolean hasCod;
	private BigDecimal codAmount;
	private String pin;
	private BigDecimal orderValue;
}
