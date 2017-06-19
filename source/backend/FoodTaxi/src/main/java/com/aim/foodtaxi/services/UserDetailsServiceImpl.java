package com.aim.foodtaxi.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.repositories.DriverRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<DriverEntity> user = driverRepository.findOneByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("There is no user with this username");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(), grantedAuthorities);
    }
}