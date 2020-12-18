package com.restaurantrest.restaurantrest.model.weather;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidatedWeatherDto {
    private Long id;
    private String weatherStateName;
    private String weatherStateAbbr;
    private String windDirectionCompass;
    private String created;
    private String applicableDate;
    private Double minTemp;
    private Double maxTemp;
    private Double theTemp;
    private Double windSpeed;
    private Double windDirection;
    private Double airPressure;
    private Integer humidity;
    private Double visibility;
    private Integer predictability;

}
