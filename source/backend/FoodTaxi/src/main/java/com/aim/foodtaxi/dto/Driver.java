package com.aim.foodtaxi.dto;

import java.util.Date;

import com.aim.foodtaxi.enums.DriverAccountStatus;
import com.aim.foodtaxi.enums.DriverStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private Date registerDate;
    private String username;
    private String password;
    private DriverAccountStatus status;
    private String cabNumber;
    private String cabPlateNumber;
    private Integer rating;
    private DriverStatus currentStatus;
    private String imagePath;
    
}
