package com.micro.HotelServices.repository;

import com.micro.HotelServices.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
