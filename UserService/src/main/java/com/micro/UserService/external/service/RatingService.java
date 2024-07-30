package com.micro.UserService.external.service;

import com.micro.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="RATINGSERVICE")
@Service
public interface RatingService {

    // create Rating Api
    @PostMapping("/rating")
    public Rating createRating(@RequestBody Rating rating);

    // update Rating api
    @PutMapping("/rating/{ratingId}")
    public Rating updateRating(@RequestBody Rating rating ,@PathVariable String ratingId);

    //Delete Mapping
    @DeleteMapping("/rating/{ratingId}")
    public void deleteRating (@PathVariable("ratingId") String ratingId );
}
