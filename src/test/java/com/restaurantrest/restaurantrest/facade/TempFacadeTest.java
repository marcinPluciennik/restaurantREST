package com.restaurantrest.restaurantrest.facade;

import com.restaurantrest.restaurantrest.client.WeatherClient;
import com.restaurantrest.restaurantrest.mapper.TempMapper;
import com.restaurantrest.restaurantrest.model.weather.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
        Source source1 = new Source("test1", "test2", "test3", 20);
        Source source2 = new Source("test4", "test5", "test6", 30);
        List<Source> sources = new ArrayList<>();
        sources.add(source1);
        sources.add(source2);
        Parent parent = new Parent("title", "locationType", 12, "lattLong");

        PragueWeatherDto pragueWeatherDto = new PragueWeatherDto(new ArrayList<>(),
                "test1", "test2", "test3", "test4", parent,
                sources, "test5", "test6",
                10, "test7", "test8");

        PragueWeather pragueWeather = new PragueWeather(new ArrayList<>(),
                "test1", "test2", "test3", "test4", parent,
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

    @Test
    public void shouldFetchConsolidatedWeather(){
        //Given
        ConsolidatedWeather consolidatedWeather = new ConsolidatedWeather(1L,
                "test1", "test2", "test3",
                "test4", "test5", 1.5, 2.5, 3.5,
                4.5, 5.5, 6.5, 10,  10.5, 20);
        ConsolidatedWeatherDto consolidatedWeatherDto = new ConsolidatedWeatherDto(1L,
                "test1", "test2", "test3",
                "test4", "test5", 1.5, 2.5, 3.5,
                4.5, 5.5, 6.5, 10,  10.5, 20);

        when(tempMapper.mapToConsolidatedWeather(consolidatedWeatherDto)).thenReturn(consolidatedWeather);
        when(weatherClient.getConsolidatedWeather()).thenReturn(consolidatedWeather);
        when(tempMapper.mapToConsolidatedWeatherDto(consolidatedWeather)).thenReturn(consolidatedWeatherDto);

        //When
        ConsolidatedWeather consolidatedWeatherTest = tempFacade.getConsolidatedWeather();

        //Then
        assertEquals(1L, consolidatedWeatherTest.getId());
        assertEquals("test1", consolidatedWeatherTest.getWeatherStateName());
        assertEquals("test2", consolidatedWeatherTest.getWeatherStateAbbr());
        assertEquals("test3", consolidatedWeatherTest.getWindDirectionCompass());
        assertEquals("test4", consolidatedWeatherTest.getCreated());
        assertEquals("test5", consolidatedWeatherTest.getApplicableDate());
        assertEquals(1.5, consolidatedWeatherTest.getMinTemp());
        assertEquals(2.5, consolidatedWeatherTest.getMaxTemp());
        assertEquals(3.5, consolidatedWeatherTest.getTheTemp());
        assertEquals(4.5, consolidatedWeatherTest.getWindSpeed());
        assertEquals(5.5, consolidatedWeatherTest.getWindDirection());
        assertEquals(6.5, consolidatedWeatherTest.getAirPressure());
        assertEquals(10, consolidatedWeatherTest.getHumidity());
        assertEquals(10.5, consolidatedWeatherTest.getVisibility());
        assertEquals(20, consolidatedWeatherTest.getPredictability());
    }
}