package com.aim.foodtaxi.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServices {

    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
