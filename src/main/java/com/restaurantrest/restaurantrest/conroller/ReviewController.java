package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.Review;
import com.restaurantrest.restaurantrest.domain.ReviewDto;
import com.restaurantrest.restaurantrest.mapper.ReviewMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "createReview", consumes = APPLICATION_JSON_VALUE)
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto){
        return reviewMapper.mapToReviewDto(service.saveReview(reviewMapper.mapToReview(reviewDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReviews")
    public List<ReviewDto> getReviews(){
        List<Review> reviews = service.getReviews();
        return reviewMapper.mapToReviewDtoList(reviews);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReview/{reviewId}")
    public ReviewDto getReviewById(@PathVariable Long reviewId) throws ReviewNotFoundException{
        return reviewMapper.mapToReviewDto(service.findReviewById(reviewId).orElseThrow(ReviewNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeReview/{reviewId}")
    public void removeReviewById(@PathVariable Long reviewId) {
        Optional<Review> reviewById = service.getReviews().stream()
                .filter(review -> review.getReviewId() == reviewId)
                .findFirst();
        if (reviewById.isPresent()) {
            service.removeReviewById(reviewId);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editReview")
    public void editReviewById(@RequestBody ReviewDto reviewDto){
        Optional<Review> reviewById = service.getReviews().stream()
                .filter(review -> review.getReviewId() == reviewDto.getReviewId())
                .findFirst();
        if (reviewById.isPresent()) {
            service.removeReviewById(reviewDto.getReviewId());
            service.saveReview(reviewMapper.mapToReview(reviewDto));
        }
    }
}
