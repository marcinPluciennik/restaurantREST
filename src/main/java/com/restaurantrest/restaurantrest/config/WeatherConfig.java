package com.restaurantrest.restaurantrest.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherConfig {

    @Value("${weather.api.endpoint}")
    private String weatherAppEndPoint;
}
