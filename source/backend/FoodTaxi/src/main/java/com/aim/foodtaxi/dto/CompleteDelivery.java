package com.aim.foodtaxi.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompleteDelivery {
	@NotNull
	private Long deliveryId;
	@NotNull
	private Boolean delivered;
	private String denialReason;
	
}
