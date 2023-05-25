package com.example.UserService.service;

import com.example.UserService.entity.Hotel;
import com.example.UserService.entity.Rating;
import com.example.UserService.exception.ResourceNotFoundException;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.entity.User;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        List<Rating> ratings = List.of(restTemplate.getForObject("http://RATING-SERVICE/Ratings/" + user.getId(), Rating[].class));
        user.setRatings(ratings);


        ratings.forEach(rating ->
                {
                    System.out.println("rating = " + rating.getRatingId());
                    ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                    Hotel hotel = forEntity.getBody();

                    rating.setHotel(hotel);
                }
        );
        user.setRatings(ratings);
//        ratings.stream().map(rating ->
//                {
//                    System.out.println("rating.getHotelId() = " + rating.getHotelId());
//                    ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8081/hotels/" + rating.getHotelId(), Hotel.class);
//                    Hotel hotel = forEntity.getBody();
//                    System.out.println("hotel.getAbout() = " + hotel.getAbout());
//
//                    rating.setHotel(hotel);
//                    return null;
//                }
//        );
//        peek.forEach(System.out::println);
        //user.setRatings(peek);
        return user;
    }
}


