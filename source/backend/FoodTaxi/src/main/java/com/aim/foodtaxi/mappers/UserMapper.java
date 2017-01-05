package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aim.foodtaxi.domain.UserEntity;
import com.aim.foodtaxi.dto.User;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    
    @Mapping(target = "password", ignore = true)
    public User userEntityToUser(UserEntity userEntity);
    
    
    UserEntity userToUserEntity(User user);
}
