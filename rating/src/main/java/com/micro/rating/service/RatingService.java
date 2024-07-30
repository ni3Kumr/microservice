package com.micro.rating.service;

import com.micro.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    // get All Rating

    List<Rating> getAllRating();

    //getRating By UserId.

    List<Rating> getRatingByUserId(String userId);

    // get rating By Hotel id

    List<Rating> getRatingByHotelId(String hotelId);
}
