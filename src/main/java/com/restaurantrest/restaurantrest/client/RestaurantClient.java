package com.restaurantrest.restaurantrest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestaurantClient {

    @Autowired
    private RestTemplate restTemplate;
}
