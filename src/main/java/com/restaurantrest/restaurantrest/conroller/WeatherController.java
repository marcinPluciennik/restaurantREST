package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.client.WeatherClient;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherClient weatherClient;

    @RequestMapping(method = RequestMethod.GET, value = "getWeather")
    public ConsolidatedWeather getWeatherInPrague() {
        return weatherClient.getConsolidatedWeather();
    }
}
