package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.dto.Brand;
import com.aim.foodtaxi.mappers.BrandMapper;
import com.aim.foodtaxi.repositories.BrandRepository;

@Service
@Transactional
public class BrandService {

//    @Inject
//    private BrandRepository brandRepository;
//
//    @Inject
//    private BrandMapper brandMapper;
//
//    public void createBrand(Brand brand) {
//        BrandEntity brandEntity = brandMapper.brandToBrandEntity(brand);
//        brandEntity.getShops().stream().forEach(shop -> shop.setBrand(brandEntity));
//        brandRepository.save(brandEntity);
//    }
//
//    public Brand getBrandById(Long brandId) {
//        Optional<BrandEntity> brandEntity = brandRepository.findOneById(brandId);
//        if (brandEntity.isPresent()) {
//            return brandMapper.brandEntityToBrand(brandEntity.get());
//        }
//        return null;
//    }

}
