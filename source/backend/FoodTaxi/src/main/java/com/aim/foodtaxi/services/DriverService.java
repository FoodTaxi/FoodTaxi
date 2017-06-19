package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.mappers.DriverMapper;
import com.aim.foodtaxi.repositories.DriverRepository;

@Service
@Transactional
public class DriverService {

    @Inject
    private DriverRepository driverRepository;

    @Inject
    private DriverMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createDriver(Driver driver) {
        DriverEntity driverEntity = userMapper.driverToDriverEntity(driver);
        driverEntity.setPassword(bCryptPasswordEncoder.encode(driverEntity.getPassword()));
        driverRepository.save(driverEntity);
    }

    public Driver getDriverById(Long dirverId) {
        Optional<DriverEntity> driverEntity = driverRepository.findOneById(dirverId);
        if (driverEntity.isPresent()) {
            return userMapper.driverEntityToDriver(driverEntity.get());
        }
        return null;
    }
}
