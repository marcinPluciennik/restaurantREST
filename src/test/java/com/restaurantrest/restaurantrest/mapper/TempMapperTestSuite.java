package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import com.restaurantrest.restaurantrest.model.weather.Parent;
import com.restaurantrest.restaurantrest.model.weather.PragueWeather;
import com.restaurantrest.restaurantrest.model.weather.PragueWeatherDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TempMapperTestSuite {

    @Autowired
    private TempMapper tempMapper;

    @Test
    public void testMapToTempDto(){
        //Given
        Temp temp = new Temp(1L, LocalDate.of(2020,12,12), 2.5);

        //When
        TempDto tempDto = tempMapper.mapToTempDto(temp);

        //Then
        long id  = tempDto.getTempId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(2.5, tempDto.getTemp(),0);
    }

    @Test
    public void testMapToTemp(){
        //Given

        Temp tempDto = new Temp(1L, LocalDate.of(2020,12,12), 2.5);

        //When
        TempDto temp = tempMapper.mapToTempDto(tempDto);

        //Then
        long id  = temp.getTempId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(2.5, tempDto.getTemp(),0);
    }

    @Test
    public void testMapToPragueWeather(){
        //Given

        PragueWeatherDto pragueWeatherDto = new PragueWeatherDto(new ArrayList<>(),
               "test1", "test2", "test3", "test4", new Parent(),
                new ArrayList<>(), "test5", "test6",
                10, "test7", "test8");

        //When
        PragueWeather pragueWeather = tempMapper.mapToPragueWeather(pragueWeatherDto);

        //Then
        Assert.assertEquals("test2", pragueWeather.getSunRise());
        Assert.assertEquals(10, pragueWeather.getWoeid(), 0);
    }

    @Test
    public void testMapToPragueWeatherDto(){
        //Given

        PragueWeather pragueWeather = new PragueWeather(new ArrayList<>(),
                "test1", "test2", "test3", "test4", new Parent(),
                new ArrayList<>(), "test5", "test6",
                10, "test7", "test8");

        //When
        PragueWeatherDto pragueWeatherDto = tempMapper.mapToPragueWeatherDto(pragueWeather);

        //Then
        Assert.assertEquals("test2", pragueWeatherDto.getSunRise());
        Assert.assertEquals(10, pragueWeatherDto.getWoeid(), 0);
    }
}