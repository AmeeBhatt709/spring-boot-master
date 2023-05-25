package com.example.ratingservice.service;

import com.example.ratingservice.entity.Rating;
import com.example.ratingservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUerId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }
    @Override
    public List<Rating> getRatingByHotelById(Long HotelId) {
        return  null;
    }
}


