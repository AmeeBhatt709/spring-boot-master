package com.example.ratingservice.service;


import com.example.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRating();
    List<Rating> getRatingByUerId(Long userId);

    List<Rating> getRatingByHotelById(Long HotelId);
}
