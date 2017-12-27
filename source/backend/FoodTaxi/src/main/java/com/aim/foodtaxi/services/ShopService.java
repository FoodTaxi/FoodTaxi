package com.aim.foodtaxi.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.BrandEntity;
import com.aim.foodtaxi.domain.ShopEntity;
import com.aim.foodtaxi.domain.ShopUserEntity;
import com.aim.foodtaxi.dto.Shop;
import com.aim.foodtaxi.mappers.ShopMapper;
import com.aim.foodtaxi.repositories.BrandRepository;
import com.aim.foodtaxi.repositories.ShopRepository;
import com.aim.foodtaxi.repositories.ShopUserRepository;

@Service
@Transactional
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ShopUserRepository shopUserRepository;

	@Autowired
	private ShopMapper shopMapper;

	public boolean createShop(Shop shop) {
		BrandEntity brandEntity = brandRepository.findOne(shop.getBrandId());
		if (brandEntity != null) {
			ShopEntity shopEntity = shopMapper.shopToShopEntity(shop);
			shopEntity.setBrand(brandEntity);
			shopRepository.save(shopEntity);
			return true;
		}
		return false;
	}

	public List<Shop> getShopsForUser(String username) {

		Optional<ShopUserEntity> user = shopUserRepository.findOneByUsername(username);

		if (!user.isPresent()) {
			throw new RuntimeException("User not found for username: " + username);
		}

		Long clientId = user.get().getClient().getId();
		List<ShopEntity> shops = shopRepository.getAllByBrandClientId(clientId);

		return shopMapper.shopEntitiesToShops(shops);
	}
}
