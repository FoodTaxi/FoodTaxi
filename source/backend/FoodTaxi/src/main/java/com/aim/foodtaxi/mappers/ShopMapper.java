package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;

import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.Shop;

@Mapper(componentModel = "spring", uses = {})
public interface ShopMapper {

    public Shop shopEntityToShop(ShopEntity shopEntity);

    public ShopEntity shopToShopEntity(Shop shop);
}
