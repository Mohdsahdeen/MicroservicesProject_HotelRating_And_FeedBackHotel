package com.ratingservice.Services.Impl;

import com.ratingservice.Services.RatingService;
import com.ratingservice.entities.Rating;
import com.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    // create rating
    @Override
    public Rating create(Rating rating) {

      String ratingId =   UUID.randomUUID().toString();
      rating.setRatingId(ratingId);

        return ratingRepository.save(rating);
    }


    // get all rating
    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
