package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyReviewDto {

    private Long reviewId;
    private String reviewText;
    private int rating;
}
