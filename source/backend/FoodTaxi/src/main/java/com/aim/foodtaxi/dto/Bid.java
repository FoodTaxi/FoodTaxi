package com.aim.foodtaxi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bid {

    private Long id;
    private Double price;
    private Date bidTime;
    private boolean isActive;
    private Long driverId;
    private String driverFullName;
    private Long deliveryId;
}