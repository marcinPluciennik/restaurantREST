package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.client.RestaurantClient;
import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.domain.MyReviewDto;
import com.restaurantrest.restaurantrest.mapper.MyReviewMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reviews")
public class MyReviewController {

    @Autowired
    private MyReviewMapper myReviewMapper;

    @Autowired
    private DbService service;

    @Autowired
    private RestaurantClient restaurantClient;

    @RequestMapping(method = RequestMethod.GET, value = "getMyReviews")
    public List<MyReviewDto> getTemps(){
        return myReviewMapper.mapToReviewDtoList(service.getReviews());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMyReview/{reviewId}")
    public MyReviewDto getTempByDate(@PathVariable Long reviewId){
        return myReviewMapper.mapToReviewDto(service.findMyReviewById(reviewId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeMyReview/{reviewId}")
    public boolean removeMyReviewById(@PathVariable Long reviewId) {
        return service.removeMyReviewById(reviewId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editMyReview")
    public void editMyReviewById(@RequestBody MyReviewDto myReviewDto){
        service.updateMyReview(myReviewMapper.mapToReview(myReviewDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addMyReview", consumes = APPLICATION_JSON_VALUE)
    public void addMyReview(@RequestBody MyReview myReview){
        service.addOneMyReview(myReview.getReviewText(), myReview.getRating());
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveTemp", consumes = APPLICATION_JSON_VALUE)
    public void saveExistingReviews(){
        service.saveExistingReviews(restaurantClient.getReviews());
    }
}
