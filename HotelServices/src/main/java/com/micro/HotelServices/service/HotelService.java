package com.micro.HotelServices.service;

import com.micro.HotelServices.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);
    List<Hotel> getAllHotelList();
    Hotel getHotelById(String id);

}
