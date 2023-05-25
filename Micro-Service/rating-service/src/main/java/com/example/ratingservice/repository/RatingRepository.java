package com.example.ratingservice.repository;

import com.example.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByUserId(Long usrId);
    List<Rating> findByHotelId(Long hotelId);
}
