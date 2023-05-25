package com.example.ratingservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    private Long userId;
        private Long hotelId;
    private Integer rating;
    private  String feedback;

}
