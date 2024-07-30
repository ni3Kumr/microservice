package com.micro.HotelServices.service;

import com.micro.HotelServices.entity.Hotel;
import com.micro.HotelServices.exception.ResourceNotFoundException;
import com.micro.HotelServices.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelserviceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId  = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel savedHotel = hotelRepository.save(hotel);
        return   savedHotel;
    }

    @Override
    public List<Hotel> getAllHotelList() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

    @Override
    public Hotel getHotelById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id Not Found !!"));
        return hotel;
    }
}
