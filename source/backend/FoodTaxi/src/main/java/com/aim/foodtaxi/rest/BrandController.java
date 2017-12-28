package com.aim.foodtaxi.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Brand;
import com.aim.foodtaxi.services.BrandService;

@RestController
@RequestMapping("/api/private/brand")
public class BrandController {

    @Inject
    private BrandService brandService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBrand(@RequestHeader(value = "authorization") String authString,
            @RequestBody Brand brand) {
        brandService.createBrand(brand);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
