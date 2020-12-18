package com.restaurantrest.restaurantrest.facade;

import com.restaurantrest.restaurantrest.client.WeatherClient;
import com.restaurantrest.restaurantrest.mapper.TempMapper;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeather;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeatherDto;
import com.restaurantrest.restaurantrest.model.weather.PragueWeather;
import com.restaurantrest.restaurantrest.model.weather.PragueWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TempFacade {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private TempMapper tempMapper;

    public PragueWeather getWeatherInPrague(){
        PragueWeatherDto pragueWeatherDto = tempMapper.mapToPragueWeatherDto(weatherClient.getWeatherInPrague());
        return tempMapper.mapToPragueWeather(pragueWeatherDto);
    }

    public ConsolidatedWeather getConsolidatedWeather(){
        ConsolidatedWeatherDto consolidatedWeatherDto = tempMapper.mapToConsolidatedWeatherDto(weatherClient.getConsolidatedWeather());
        return tempMapper.mapToConsolidatedWeather(consolidatedWeatherDto);
    }
}
