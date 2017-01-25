package com.aim.foodtaxi.services;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.mappers.DriverMapper;
import com.aim.foodtaxi.repositories.DriverRepository;

@Service
@Transactional
public class DriverService  {

    @Inject
    private DriverRepository driverRepository;

    @Inject
    private DriverMapper userMapper;

    public void createDriver(Driver driver) {
        DriverEntity driverEntity = userMapper.driverToDriverEntity(driver);
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
