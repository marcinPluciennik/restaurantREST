package com.restaurantrest.restaurantrest.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PragueWeatherDto {

    private List<ConsolidatedWeather> consolidatedWeather = null;
    private String time;
    private String sunRise;
    private String sunSet;
    private String timezoneName;
    private Parent parent;
    private List<Source> sources = null;
    private String title;
    private String locationType;
    private Integer woeid;
    private String lattLong;
    private String timezone;
}
