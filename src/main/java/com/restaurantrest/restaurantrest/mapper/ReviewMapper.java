package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Review;
import com.restaurantrest.restaurantrest.domain.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review mapToReview(final ReviewDto reviewDto){
        return new Review(
                reviewDto.getReviewId(),
                reviewDto.getReviewText(),
                reviewDto.getRating()
        );
    }

    public ReviewDto mapToReviewDto(final Review review){
        return new ReviewDto(
                review.getReviewId(),
                review.getReviewText(),
                review.getRating()
        );
    }
}
