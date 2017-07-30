package com.aim.foodtaxi.mappers;

import org.mapstruct.Mapper;

import com.aim.foodtaxi.domain.ClientEntity;
import com.aim.foodtaxi.dto.Client;

@Mapper(componentModel = "spring", uses = {})
public interface ClientMapper {

    public Client clientEntityToClient(ClientEntity clientEntity);
        
    public ClientEntity clientToClientEntity(Client client);
}
