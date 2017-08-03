package com.aim.foodtaxi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Brand {
    private Long id;
    private String name;
    private String logoPath;
    private List<Shop> shops;
    private Long clientId;
}
