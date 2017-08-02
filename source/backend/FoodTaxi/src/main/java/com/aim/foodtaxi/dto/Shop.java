package com.aim.foodtaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Shop {

    private Long id;
    private String name;
    private Double longtitude;
    private Double latitude;
    private String ordersPhone;
    private String ordersEmail;
    private String ordersSMS;
    private String addressText;
    private Long brandId;
}
