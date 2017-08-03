package com.aim.foodtaxi.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.Brand;
import com.aim.foodtaxi.dto.Shop;

@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface BrandMapper {
    
    @Named("brandEntityToBrand")
    public Brand brandEntityToBrand(BrandEntity brandEntity);

    @Named("brandToBrandEntity")
    public BrandEntity brandToBrandEntity(Brand brand);

    @IterableMapping(qualifiedByName = "brandEntityToBrand")
    public List<Shop> map(List<ShopEntity> shopsEntities);
    
    @IterableMapping(qualifiedByName = "brandToBrandEntity")
    public List<ShopEntity> shopsToShopEntities(List<Shop> shops);

}
