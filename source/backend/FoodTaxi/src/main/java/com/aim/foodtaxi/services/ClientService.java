package com.aim.foodtaxi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aim.foodtaxi.domain.ClientEntity;
import com.aim.foodtaxi.dto.Client;
import com.aim.foodtaxi.mappers.ClientMapper;
import com.aim.foodtaxi.repositories.ClientRepository;

@Service
@Transactional(readOnly = true)
public class ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientRepository clientRepositry;
 
    @Transactional(readOnly=false)
    public void createClient(Client client) {
        ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
        //TODO: think about app key generation
        clientRepositry.save(clientEntity);
    }
}
