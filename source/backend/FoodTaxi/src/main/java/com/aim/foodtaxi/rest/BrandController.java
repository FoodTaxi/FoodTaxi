package com.aim.foodtaxi.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aim.foodtaxi.dto.Brand;
import com.aim.foodtaxi.services.BrandService;

@RestController
@RequestMapping("/api")
public class BrandController {

//    @Inject
//    private BrandService brandService;
//
//    @RequestMapping(value = "/brand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createBrand(@RequestHeader(value = "authorization") String authString,
//            @RequestBody Brand brand) {
//        brandService.createBrand(brand);
//        return new ResponseEntity<>(null, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/brand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getBrand(@RequestHeader(value = "authorization") String authString,
//            @RequestParam Long brandId) {
//        Brand brand = brandService.getBrandById(brandId);
//        return new ResponseEntity<>(brand, HttpStatus.OK);
//    }

}
