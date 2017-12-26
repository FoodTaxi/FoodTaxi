package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.dto.Brand;

@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface BrandMapper {
    
    @Named("brandEntityToBrand")
    public Brand brandEntityToBrand(BrandEntity brandEntity);

    @Named("brandToBrandEntity")
    public BrandEntity brandToBrandEntity(Brand brand);
}
