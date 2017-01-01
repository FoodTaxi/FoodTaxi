package com.aim.foodtaxi.services;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aim.foodtaxi.domain.UserEntity;
import com.aim.foodtaxi.dto.User;
import com.aim.foodtaxi.mappers.UserMapper;
import com.aim.foodtaxi.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    public void createUser(User user) {
        UserEntity userEntity = userMapper.userToUserEntity(user);
        userRepository.save(userEntity);
    }
}
