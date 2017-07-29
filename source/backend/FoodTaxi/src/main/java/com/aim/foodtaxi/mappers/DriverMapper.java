package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.dto.Driver;

@Mapper(componentModel = "spring", uses = {})
public interface DriverMapper {
    
 
    @Mapping(target = "password", ignore = true)
    public Driver driverEntityToDriver(DriverEntity diverEntity);
    
    
    DriverEntity driverToDriverEntity(Driver user);
}
