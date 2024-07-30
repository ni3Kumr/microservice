package com.micro.HotelServices.controller;

import com.micro.HotelServices.entity.Hotel;
import com.micro.HotelServices.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody  Hotel hotel){
        Hotel savedHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    // get all
    @GetMapping
    ResponseEntity<List<Hotel>> getAllHotels(){

        List<Hotel> hotelList = hotelService.getAllHotelList();
        return ResponseEntity.ok(hotelList);

    }
    // get by id
    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotelById(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(id));

    }

}
