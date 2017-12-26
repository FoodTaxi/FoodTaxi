package com.aim.foodtaxi.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.Shop;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopMapper {

	@Named("shopEntityToShop")
	public Shop shopEntityToShop(ShopEntity shopEntity);

	@Named("shopToShopEntity")
	public ShopEntity shopToShopEntity(Shop shop);
	
	@IterableMapping(qualifiedByName="shopEntityToShop")
	public List<Shop> shopEntitiesToShops(List<ShopEntity> shopEntities);

	@IterableMapping(qualifiedByName="shopToShopEntity")
	public List<ShopEntity> shopsToShopEntities(List<Shop> shops);

}
