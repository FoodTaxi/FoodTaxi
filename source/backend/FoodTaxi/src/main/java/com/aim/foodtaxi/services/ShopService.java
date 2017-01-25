package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.dto.Shop;
import com.aim.foodtaxi.mappers.ShopMapper;
import com.aim.foodtaxi.repositories.ShopRepository;

@Service
@Transactional
public class ShopService {
    
    @Inject
    private ShopRepository shopRepository;

    @Inject
    private ShopMapper shopMapper;

    public void createShop(Shop shop) {
        ShopEntity shopEntity = shopMapper.shopToShopEntity(shop);
        shopRepository.save(shopEntity);
    }

    public Shop getShopById(Long shopId) {
        Optional<ShopEntity> shopEntity = shopRepository.findOneById(shopId);
        if (shopEntity.isPresent()) {
            return shopMapper.shopEntityToShop(shopEntity.get());
        }
        return null;
    }

}
