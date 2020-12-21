package com.restaurantrest.restaurantrest.facade;

import com.restaurantrest.restaurantrest.client.WeatherClient;
import com.restaurantrest.restaurantrest.mapper.TempMapper;
import com.restaurantrest.restaurantrest.model.weather.Parent;
import com.restaurantrest.restaurantrest.model.weather.PragueWeather;
import com.restaurantrest.restaurantrest.model.weather.PragueWeatherDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TempFacadeTest {

    @InjectMocks
    private TempFacade tempFacade;

    @Mock
    private TempMapper tempMapper;

    @Mock
    private WeatherClient weatherClient;

    @Test
    public void shouldFetchWeatherInPrague(){
        //Given
        PragueWeatherDto pragueWeatherDto = new PragueWeatherDto(new ArrayList<>(),
                "test1", "test2", "test3", "test4", new Parent(),
                new ArrayList<>(), "test5", "test6",
                10, "test7", "test8");

        PragueWeather pragueWeather = new PragueWeather(new ArrayList<>(),
                "test1", "test2", "test3", "test4", new Parent(),
                new ArrayList<>(), "test5", "test6",
                10, "test7", "test8");

        when(tempMapper.mapToPragueWeather(pragueWeatherDto)).thenReturn(pragueWeather);
        when(weatherClient.getWeatherInPrague()).thenReturn(pragueWeather);
        when(tempMapper.mapToPragueWeatherDto(pragueWeather)).thenReturn(pragueWeatherDto);

        //When

        PragueWeather pragueWeatherTest = tempFacade.getWeatherInPrague();

        //Then
        assertEquals("test1", pragueWeatherTest.getTime());
        assertEquals("test2", pragueWeatherTest.getSunRise());
        assertEquals(10, pragueWeatherTest.getWoeid(), 0);
        assertEquals("test7", pragueWeatherTest.getLattLong());
        assertEquals("test8", pragueWeatherTest.getTimezone());
    }
}