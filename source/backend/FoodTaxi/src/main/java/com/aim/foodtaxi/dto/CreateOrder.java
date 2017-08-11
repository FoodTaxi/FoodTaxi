package com.aim.foodtaxi.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrder {
    
    private Long clientId;
    private Long brandId;
    private String itemDescription;
    private Date dueDate;
    private Double longtitude;
    private Double latitude;
    private String address;
    private boolean hasCod;
    private BigDecimal codAmount;
    private BigDecimal orderValue;
}
