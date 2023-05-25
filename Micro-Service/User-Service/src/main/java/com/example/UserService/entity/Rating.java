package com.example.UserService.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    private String ratingId;
    private String userId;
    private Integer hotelId;
    private Integer rating;
    private String remark;
    private String feedback;

    private Hotel hotel;




}
