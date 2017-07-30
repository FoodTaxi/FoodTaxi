package com.aim.foodtaxi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Client {
    private Long id;
    private String name;
    private String code;
    private String companyNumber;
    private String email;
    private String address;
    private String appKey;
}
