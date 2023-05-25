package com.example.hotelservice.service;

import com.example.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotels);

    List<Hotel> getAllHotel();

    Hotel getHotel(Long hotelId);

}
