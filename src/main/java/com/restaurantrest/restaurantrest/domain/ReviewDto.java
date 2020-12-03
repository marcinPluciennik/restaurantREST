package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewDto {

    private Long reviewId;
    private String reviewText;
    private double rating;
}
