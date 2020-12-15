package com.restaurantrest.restaurantrest.client;

import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeather;
import com.restaurantrest.restaurantrest.model.weather.PragueWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public PragueWeather getWeatherInPrague(){
        try{
            PragueWeather weatherInPrague = restTemplate.getForObject("https://www.metaweather.com/api/location/796597",
                    PragueWeather.class);
            return weatherInPrague;
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new PragueWeather();
        }
    }

    public ConsolidatedWeather getConsolidatedWeather(){
        ConsolidatedWeather consolidatedWeather = getWeatherInPrague().getConsolidatedWeather().get(0);
        if (consolidatedWeather != null) {
            return consolidatedWeather;
        }
        return new ConsolidatedWeather();
    }
}
