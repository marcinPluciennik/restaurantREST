package com.restaurantrest.restaurantrest.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RestaurantConfig {

    @Value("${zomato.app.user.key}")
    private String zomatoAppUserKey;
}
