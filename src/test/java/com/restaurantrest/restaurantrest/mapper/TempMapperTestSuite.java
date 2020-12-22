package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import com.restaurantrest.restaurantrest.model.weather.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        //When
        PragueWeather pragueWeather = tempMapper.mapToPragueWeather(pragueWeatherDto);

        //Then
        Assert.assertEquals("test2", pragueWeather.getSunRise());
        Assert.assertEquals("test1", pragueWeather.getTime());
        Assert.assertEquals("test3", pragueWeather.getSunSet());
        Assert.assertEquals("test4", pragueWeather.getTimezoneName());
        Assert.assertEquals("test5", pragueWeather.getTitle());
        Assert.assertEquals("test6", pragueWeather.getLocationType());
        Assert.assertEquals("test8", pragueWeather.getTimezone());
        Assert.assertEquals("test7", pragueWeather.getLattLong());
        Assert.assertEquals(10, pragueWeather.getWoeid(), 0);
        Assert.assertEquals(new ArrayList<>(), pragueWeather.getConsolidatedWeather());
        Assert.assertEquals("title", pragueWeather.getParent().getTitle());
        Assert.assertEquals("locationType", pragueWeather.getParent().getLocationType());
        Assert.assertEquals(12, pragueWeather.getParent().getWoeid(), 0);
        Assert.assertEquals("lattLong", pragueWeather.getParent().getLattLong());
        Assert.assertEquals("test1", pragueWeather.getSources().get(0).getTitle());
        Assert.assertEquals("test2", pragueWeather.getSources().get(0).getSlug());
        Assert.assertEquals("test3", pragueWeather.getSources().get(0).getUrl());
        Assert.assertEquals(20, pragueWeather.getSources().get(0).getCrawlRate(), 0);



    }

    @Test
    public void testMapToPragueWeatherDto(){
        //Given
        Parent parent = new Parent("title", "locationType", 12, "lattLong");
        Source source1 = new Source("test1", "test2", "test3", 20);
        Source source2 = new Source("test4", "test5", "test6", 30);
        List<Source> sources = new ArrayList<>();
        sources.add(source1);
        sources.add(source2);


        PragueWeather pragueWeather = new PragueWeather(new ArrayList<>(),
                "test1", "test2", "test3", "test4", parent,
                sources, "test5", "test6",
                10, "test7", "test8");

        //When
        PragueWeatherDto pragueWeatherDto = tempMapper.mapToPragueWeatherDto(pragueWeather);

        //Then
        Assert.assertEquals("test2", pragueWeatherDto.getSunRise());
        Assert.assertEquals("test1", pragueWeatherDto.getTime());
        Assert.assertEquals("test3", pragueWeatherDto.getSunSet());
        Assert.assertEquals("test4", pragueWeatherDto.getTimezoneName());
        Assert.assertEquals("test5", pragueWeatherDto.getTitle());
        Assert.assertEquals("test6", pragueWeatherDto.getLocationType());
        Assert.assertEquals("test8", pragueWeatherDto.getTimezone());
        Assert.assertEquals("test7", pragueWeatherDto.getLattLong());
        Assert.assertEquals(10, pragueWeatherDto.getWoeid(), 0);
        Assert.assertEquals(new ArrayList<>(), pragueWeatherDto.getConsolidatedWeather());
        Assert.assertEquals("title", pragueWeatherDto.getParent().getTitle());
        Assert.assertEquals("locationType", pragueWeatherDto.getParent().getLocationType());
        Assert.assertEquals(12, pragueWeatherDto.getParent().getWoeid(), 0);
        Assert.assertEquals("lattLong", pragueWeatherDto.getParent().getLattLong());
        Assert.assertEquals("test1", pragueWeatherDto.getSources().get(0).getTitle());
        Assert.assertEquals("test2", pragueWeatherDto.getSources().get(0).getSlug());
        Assert.assertEquals("test3", pragueWeatherDto.getSources().get(0).getUrl());
        Assert.assertEquals(20, pragueWeatherDto.getSources().get(0).getCrawlRate(), 0);

    }

    @Test
    public void testMapToTempDtoList(){
        //Given
        Temp temp1 = new Temp(1L, LocalDate.of(2020,12,12), 2.5);
        Temp temp2 = new Temp(2L, LocalDate.of(2020,12,10), 1.5);
        Temp temp3 = new Temp(3L, LocalDate.of(2020,12,11), 5.5);
        List<Temp> tempList = new ArrayList<>();
        tempList.add(temp1);
        tempList.add(temp2);
        tempList.add(temp3);

        //When
        List<TempDto> tempDtoList = tempMapper.mapToTempDtoList(tempList);

        //Then
        long id  = tempDtoList.get(0).getTempId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(LocalDate.of(2020,12,12), tempDtoList.get(0).getDate());
        Assert.assertEquals(2.5, tempDtoList.get(0).getTemp(),0);
    }

    @Test
    public void testMapToConsolidatedWeather(){
        //Given
        ConsolidatedWeatherDto consolidatedWeatherDto = new ConsolidatedWeatherDto(1L,
                "test1", "test2", "test3",
                "test4", "test5", 1.5, 2.5, 3.5,
                4.5, 5.5, 6.5, 10,  10.5, 20);

        //When
        ConsolidatedWeather consolidatedWeather = tempMapper.mapToConsolidatedWeather(consolidatedWeatherDto);

        //Then
        Assert.assertEquals(1L, consolidatedWeather.getId(), 0);
        Assert.assertEquals("test1", consolidatedWeather.getWeatherStateName());
        Assert.assertEquals("test2", consolidatedWeather.getWeatherStateAbbr());
        Assert.assertEquals("test3", consolidatedWeather.getWindDirectionCompass());
        Assert.assertEquals("test4", consolidatedWeather.getCreated());
        Assert.assertEquals("test5", consolidatedWeather.getApplicableDate());
        Assert.assertEquals(1.5, consolidatedWeather.getMinTemp(), 0);
        Assert.assertEquals(2.5, consolidatedWeather.getMaxTemp(), 0);
        Assert.assertEquals(3.5, consolidatedWeather.getTheTemp(), 0);
        Assert.assertEquals(4.5, consolidatedWeather.getWindSpeed(), 0);
        Assert.assertEquals(5.5, consolidatedWeather.getWindDirection(),0);
        Assert.assertEquals(6.5, consolidatedWeather.getAirPressure(),0);
        Assert.assertEquals(10, consolidatedWeather.getHumidity(),0);
        Assert.assertEquals(10.5, consolidatedWeather.getVisibility(),0);
        Assert.assertEquals(20, consolidatedWeather.getPredictability(),0);
    }

    @Test
    public void testMapToConsolidatedWeatherDto(){
        //Given
        ConsolidatedWeather consolidatedWeather = new ConsolidatedWeather(1L,
                "test1", "test2", "test3",
                "test4", "test5", 1.5, 2.5, 3.5,
                4.5, 5.5, 6.5, 10,  10.5, 20);

        //When
        ConsolidatedWeatherDto consolidatedWeatherDto = tempMapper.mapToConsolidatedWeatherDto(consolidatedWeather);

        //Then
        Assert.assertEquals(1L, consolidatedWeatherDto.getId(), 0);
        Assert.assertEquals("test1", consolidatedWeatherDto.getWeatherStateName());
        Assert.assertEquals("test2", consolidatedWeatherDto.getWeatherStateAbbr());
        Assert.assertEquals("test3", consolidatedWeatherDto.getWindDirectionCompass());
        Assert.assertEquals("test4", consolidatedWeatherDto.getCreated());
        Assert.assertEquals("test5", consolidatedWeatherDto.getApplicableDate());
        Assert.assertEquals(1.5, consolidatedWeatherDto.getMinTemp(), 0);
        Assert.assertEquals(2.5, consolidatedWeatherDto.getMaxTemp(), 0);
        Assert.assertEquals(3.5, consolidatedWeatherDto.getTheTemp(), 0);
        Assert.assertEquals(4.5, consolidatedWeatherDto.getWindSpeed(), 0);
        Assert.assertEquals(5.5, consolidatedWeatherDto.getWindDirection(),0);
        Assert.assertEquals(6.5, consolidatedWeatherDto.getAirPressure(),0);
        Assert.assertEquals(10, consolidatedWeatherDto.getHumidity(),0);
        Assert.assertEquals(10.5, consolidatedWeatherDto.getVisibility(),0);
        Assert.assertEquals(20, consolidatedWeatherDto.getPredictability(),0);
    }
}