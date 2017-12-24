package com.aim.foodtaxi.dto;

import java.util.Date;

import com.aim.foodtaxi.enums.DeliveryStatus;
import com.aim.foodtaxi.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {

    private Long id;
    private OrderStatus status;
    private Date orderDate;
    private Double price;
    private String itemDescription;
    private Long brandId;
    private Long clientId;
    private Long shopId;
    private String shopAddressText;
    private String addressText;
    private DeliveryStatus deliveryStatus;
    private String pin;
}
