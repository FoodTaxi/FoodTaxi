package com.aim.foodtaxi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.DriverEntity;
import com.aim.foodtaxi.dto.Driver;
import com.aim.foodtaxi.enums.DriverAccountStatus;
import com.aim.foodtaxi.mappers.DriverMapper;
import com.aim.foodtaxi.repositories.DriverRepository;

@Service
@Transactional(readOnly=true)
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly=false)
    public void createDriver(Driver driver) {
        DriverEntity driverEntity = driverMapper.driverToDriverEntity(driver);
        driverEntity.setRegisterDate(new Date());
        driverEntity.setRating(5);
        //TODO: Maybe first driver account status will be awaiting
        driverEntity.setStatus(DriverAccountStatus.ACTIVE);
        //TODO: Add password encryption at some point
//        driverEntity.setPassword(bCryptPasswordEncoder.encode(driverEntity.getPassword()));
        driverRepository.save(driverEntity);
    }
    
    public boolean authenticate(String username, String password){
    	return driverRepository.findOneByUsernameAndPassword(username, password).isPresent();
    }
}
