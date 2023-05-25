package com.example.ratingservice.controller;

import com.example.ratingservice.entity.Rating;
import com.example.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;


    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(rating));
    }


    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings()
    {

        return ResponseEntity.ok(ratingService.getAllRating());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable Long userId)
    {

        return ResponseEntity.ok(ratingService.getRatingByUerId(userId));
    }
    @GetMapping("/hotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable Long hotelId)
    {
        return ResponseEntity.ok(ratingService.getRatingByHotelById(hotelId));
    }



}
