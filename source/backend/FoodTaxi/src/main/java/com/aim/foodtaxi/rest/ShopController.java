package com.aim.foodtaxi.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Shop;
import com.aim.foodtaxi.services.ShopService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/private/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createShop(@RequestHeader(value = "authorization") String authString, @RequestBody Shop shop) {
        shopService.createShop(shop);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('shop')")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Shop>> getShopsForUser(@RequestHeader(value = "authorization") String authString, @ApiIgnore Principal principal) {
        List<Shop> shops = shopService.getShopsForUser(principal.getName());
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }
}
