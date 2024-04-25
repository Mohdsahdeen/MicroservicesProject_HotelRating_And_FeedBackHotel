package com.ratingservice.Services;

import com.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    // create rating
    Rating create(Rating rating);

    // get all rating
    List<Rating> getRatings();


    // get all by userId
    List<Rating> getRatingByUserId(String userId);

    // get all by hotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
