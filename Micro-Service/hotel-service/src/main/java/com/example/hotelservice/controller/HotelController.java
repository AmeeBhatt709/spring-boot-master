package com.example.hotelservice.controller;
import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;


    @PostMapping
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.saveHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public  ResponseEntity<Hotel> findById(@PathVariable Long hotelId)
    {
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel()
    {
        return ResponseEntity.ok(hotelService.getAllHotel());
    }


}
