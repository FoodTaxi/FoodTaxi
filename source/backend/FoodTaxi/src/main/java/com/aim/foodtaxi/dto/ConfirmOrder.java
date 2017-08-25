package com.aim.foodtaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConfirmOrder {

	private Long orderId;
	private boolean confirmed;
	private Integer completionMinutes;
}
