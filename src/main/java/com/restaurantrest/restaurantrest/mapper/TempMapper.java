package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeather;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeatherDto;
import com.restaurantrest.restaurantrest.model.weather.PragueWeather;
import com.restaurantrest.restaurantrest.model.weather.PragueWeatherDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TempMapper {

    public Temp mapToTemp(final TempDto tempDto){
        return new Temp(
                tempDto.getTempId(),
                tempDto.getDate(),
                tempDto.getTemp());
    }

    public TempDto mapToTempDto(final Temp temp){
        return new TempDto(
                temp.getTempId(),
                temp.getDate(),
                temp.getTemp());
    }

    public List<TempDto> mapToTempDtoList(final List<Temp> tempList){
        return tempList.stream()
                .map(t -> new TempDto(t.getTempId(), t.getDate(), t.getTemp()))
                .collect(Collectors.toList());
    }

    public PragueWeather mapToPragueWeather(final PragueWeatherDto pragueWeatherDto){
        return new PragueWeather(
                pragueWeatherDto.getConsolidatedWeather(),
                pragueWeatherDto.getTime(),
                pragueWeatherDto.getSunRise(),
                pragueWeatherDto.getSunSet(),
                pragueWeatherDto.getTimezoneName(),
                pragueWeatherDto.getParent(),
                pragueWeatherDto.getSources(),
                pragueWeatherDto.getTitle(),
                pragueWeatherDto.getLocationType(),
                pragueWeatherDto.getWoeid(),
                pragueWeatherDto.getLattLong(),
                pragueWeatherDto.getTimezone());
    }

    public PragueWeatherDto mapToPragueWeatherDto(final PragueWeather pragueWeather){
        return new PragueWeatherDto(
                pragueWeather.getConsolidatedWeather(),
                pragueWeather.getTime(),
                pragueWeather.getSunRise(),
                pragueWeather.getSunSet(),
                pragueWeather.getTimezoneName(),
                pragueWeather.getParent(),
                pragueWeather.getSources(),
                pragueWeather.getTitle(),
                pragueWeather.getLocationType(),
                pragueWeather.getWoeid(),
                pragueWeather.getLattLong(),
                pragueWeather.getTimezone());
    }

    public ConsolidatedWeather mapToConsolidatedWeather(final ConsolidatedWeatherDto consolidatedWeatherDto){
        return new ConsolidatedWeather(
                consolidatedWeatherDto.getId(),
                consolidatedWeatherDto.getWeatherStateName(),
                consolidatedWeatherDto.getWeatherStateAbbr(),
                consolidatedWeatherDto.getWindDirectionCompass(),
                consolidatedWeatherDto.getCreated(),
                consolidatedWeatherDto.getApplicableDate(),
                consolidatedWeatherDto.getMinTemp(),
                consolidatedWeatherDto.getMaxTemp(),
                consolidatedWeatherDto.getTheTemp(),
                consolidatedWeatherDto.getWindSpeed(),
                consolidatedWeatherDto.getWindDirection(),
                consolidatedWeatherDto.getAirPressure(),
                consolidatedWeatherDto.getHumidity(),
                consolidatedWeatherDto.getVisibility(),
                consolidatedWeatherDto.getPredictability());
    }

    public ConsolidatedWeatherDto mapToConsolidatedWeatherDto(final ConsolidatedWeather consolidatedWeather){
        return new ConsolidatedWeatherDto(
                consolidatedWeather.getId(),
                consolidatedWeather.getWeatherStateName(),
                consolidatedWeather.getWeatherStateAbbr(),
                consolidatedWeather.getWindDirectionCompass(),
                consolidatedWeather.getCreated(),
                consolidatedWeather.getApplicableDate(),
                consolidatedWeather.getMinTemp(),
                consolidatedWeather.getMaxTemp(),
                consolidatedWeather.getTheTemp(),
                consolidatedWeather.getWindSpeed(),
                consolidatedWeather.getWindDirection(),
                consolidatedWeather.getAirPressure(),
                consolidatedWeather.getHumidity(),
                consolidatedWeather.getVisibility(),
                consolidatedWeather.getPredictability());
    }
}
