package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.Shop;
import com.aim.foodtaxi.mappers.ShopMapper;
import com.aim.foodtaxi.repositories.BrandRepository;
import com.aim.foodtaxi.repositories.ShopRepository;

@Service
@Transactional
public class ShopService {

//    @Inject
//    private ShopRepository shopRepository;
//
//    @Inject
//    private BrandRepository brandRepository;
//
//    @Inject
//    private ShopMapper shopMapper;
//
//    public boolean createShop(Long brandId, Shop shop) {
//        Optional<BrandEntity> brandEntity = brandRepository.findOneById(brandId);
//        if (brandEntity.isPresent()) {
//            ShopEntity shopEntity = shopMapper.shopToShopEntity(shop);
////            brandEntity.get().getShops().add(shopEntity);
//            shopEntity.setBrand(brandEntity.get());
//            shopRepository.save(shopEntity);
//            return true;
//        } 
//        return false;
//    }
//
//    public Shop getShopById(Long shopId) {
//        Optional<ShopEntity> shopEntity = shopRepository.findOneById(shopId);
//        if (shopEntity.isPresent()) {
//            return shopMapper.shopEntityToShop(shopEntity.get());
//        }
//        return null;
//    }

}
