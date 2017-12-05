package com.aim.foodtaxi.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.domain.ShopUserEntity;
import com.aim.foodtaxi.repositories.DriverRepository;
import com.aim.foodtaxi.repositories.ShopUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ShopUserRepository shopUserRepository;
    
    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

    	
    	String password = null;
    	Optional<DriverEntity> driverUser = driverRepository.findOneByUsername(username);
        if (driverUser.isPresent()) {
        	password = driverUser.get().getPassword();
        } else {
        	Optional<ShopUserEntity> shopUser = shopUserRepository.findOneByUsername(username);
        	if(shopUser.isPresent()){
        		password = shopUser.get().getPassword();
        	} else{
        		throw new UsernameNotFoundException("There is no user with this username");
        	}
        }
        return new org.springframework.security.core.userdetails.User(username,
                password, new HashSet<GrantedAuthority>());
    }
}