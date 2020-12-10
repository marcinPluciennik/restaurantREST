package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Review;
import com.restaurantrest.restaurantrest.domain.ReviewDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

    public Review mapToReview(final ReviewDto reviewDto){
        return new Review(
                reviewDto.getReviewId(),
                reviewDto.getReviewText(),
                reviewDto.getRating());
    }

    public ReviewDto mapToReviewDto(final Review review){
        return new ReviewDto(
                review.getReviewId(),
                review.getReviewText(),
                review.getRating());
    }

    public List<ReviewDto> mapToReviewDtoList(final List<Review> reviewList){
        return reviewList.stream()
                .map(r -> new ReviewDto(r.getReviewId(), r.getReviewText(), r.getRating()))
                .collect(Collectors.toList());
    }
}
