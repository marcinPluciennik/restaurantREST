package com.restaurantrest.restaurantrest.client;

import com.restaurantrest.restaurantrest.config.WeatherConfig;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeather;
import com.restaurantrest.restaurantrest.model.weather.Parent;
import com.restaurantrest.restaurantrest.model.weather.PragueWeather;
import com.restaurantrest.restaurantrest.model.weather.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WeatherClientTest {
    @InjectMocks
    private WeatherClient weatherClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherConfig weatherConfig;

    @Test
    public void shouldFetchWeatherInPrague(){
        //Given
        ConsolidatedWeather consolidatedWeather = new ConsolidatedWeather(1L,
                "test1", "test2", "test3",
                "test4", "test5", 1.5, 2.5, 3.5,
                4.5, 5.5, 6.5, 10,  10.5, 20);
        List<ConsolidatedWeather> consolidatedWeathers = new ArrayList<>();
        consolidatedWeathers.add(consolidatedWeather);

        Parent parent = new Parent("title", "locationType", 12, "lattLong");
        Source source1 = new Source("test1", "test2", "test3", 20);
        Source source2 = new Source("test4", "test5", "test6", 30);
        List<Source> sources = new ArrayList<>();
        sources.add(source1);
        sources.add(source2);
        PragueWeather weatherInPrague = new PragueWeather(consolidatedWeathers,
                "test1", "test2", "test3", "test4", parent,
                sources, "test5", "test6",
                10, "test7", "test8");

        when(weatherConfig.getWeatherAppEndPoint()).thenReturn("https://www.metaweather.com/api/location/796597");
        when(restTemplate.getForObject(weatherConfig.getWeatherAppEndPoint(), PragueWeather.class)).thenReturn(weatherInPrague);

        //When
        PragueWeather fetchedPragueWeather = weatherClient.getWeatherInPrague();

        //Then
        assertEquals("Prague", fetchedPragueWeather.getTitle());
        assertEquals("City", fetchedPragueWeather.getLocationType());
        assertEquals(796597, fetchedPragueWeather.getWoeid(), 0);
        assertEquals("50.079079,14.433220", fetchedPragueWeather.getLattLong());
        assertEquals("Europe/Prague", fetchedPragueWeather.getTimezone());
    }
}