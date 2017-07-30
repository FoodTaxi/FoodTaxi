package com.aim.foodtaxi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Client;
import com.aim.foodtaxi.services.ClientService;

@RestController
@RequestMapping("/api/private/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginDriver(@RequestHeader(value = "authorization") String authString,
            @RequestBody(required = true) Client client) {
        clientService.createClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
