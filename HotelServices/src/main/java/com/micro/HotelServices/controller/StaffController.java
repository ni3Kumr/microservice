package com.micro.HotelServices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @GetMapping
    ResponseEntity<List<String>> getStaff(){
        List<String> list = Arrays.asList("Nithin","Monti","Sachin","Nishant");
        return  new ResponseEntity<>(list, HttpStatus.OK);

    }
}
