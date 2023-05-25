package com.example.hotelservice.service.HotelServiceImpl;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.exception.ResourceNotFoundException;
import com.example.hotelservice.repository.HotelRepository;
import com.example.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService
{

    private final HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Resource Not Found"));
    }
}
