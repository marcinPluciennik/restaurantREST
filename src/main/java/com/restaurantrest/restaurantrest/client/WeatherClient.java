package com.restaurantrest.restaurantrest.client;

import com.restaurantrest.restaurantrest.config.WeatherConfig;
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

    private WeatherConfig weatherConfig;

    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);

    @Autowired
    public WeatherClient(WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
        this.restTemplate = new RestTemplate();
    }

    public PragueWeather getWeatherInPrague(){
        try{
            PragueWeather weatherInPrague = restTemplate.getForObject(weatherConfig.getWeatherAppEndPoint(),
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
