package com.aim.foodtaxi.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.domain.ClientEntity;
import com.aim.foodtaxi.dto.Brand;
import com.aim.foodtaxi.mappers.BrandMapper;
import com.aim.foodtaxi.repositories.BrandRepository;
import com.aim.foodtaxi.repositories.ClientRepository;

@Service
@Transactional
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private BrandMapper brandMapper;

    public void createBrand(Brand brand) {
        ClientEntity client = clientRepository.findOne(brand.getClientId());
        if (client != null) {
            BrandEntity brandEntity = brandMapper.brandToBrandEntity(brand);
            brandEntity.setClient(client);
            brandEntity.getShops().stream().forEach(shop -> shop.setBrand(brandEntity));
            brandRepository.save(brandEntity);
        }
    }
//
//    public Brand getBrandById(Long brandId) {
//        Optional<BrandEntity> brandEntity = brandRepository.findOneById(brandId);
//        if (brandEntity.isPresent()) {
//            return brandMapper.brandEntityToBrand(brandEntity.get());
//        }
//        return null;
//    }

}
