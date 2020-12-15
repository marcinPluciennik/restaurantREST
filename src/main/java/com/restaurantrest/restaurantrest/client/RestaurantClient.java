package com.restaurantrest.restaurantrest.client;


import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.model.review.Reviews;
import com.restaurantrest.restaurantrest.model.review.UserReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RestaurantClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantClient.class);

    private RestTemplate restTemplate;

    @Autowired
    public RestaurantClient() {
        this.restTemplate = new RestTemplate();
        getReviews();
    }

    public List<UserReview> getUserReviews(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("user-key", "482c662e46d7577aedbac1daeb21ba25");
        HttpEntity httpEntity = new HttpEntity(headers);

        try{
            ResponseEntity<Reviews> reviews = restTemplate.exchange(
                    "https://developers.zomato.com/api/v2.1/reviews?res_id=24127336",
                    HttpMethod.GET,
                    httpEntity,
                    Reviews.class);
            Stream.of(reviews.getBody().getUserReviews()).forEach(System.out::println);
            return reviews.getBody().getUserReviews();
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<MyReview> getReviews(){
        List<MyReview> myReviews = new ArrayList<>();
        getUserReviews().stream()
                .map(r -> myReviews.add(new MyReview(r.getReview().getId(), r.getReview().getRatingText(), r.getReview().getRating())))
                .collect(Collectors.toList());
        System.out.println("-----------------------------------------");
        Stream.of(myReviews).forEach(System.out::println);
        return myReviews;
    }
}
