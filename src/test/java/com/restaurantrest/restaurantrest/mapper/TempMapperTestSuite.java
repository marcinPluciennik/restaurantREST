package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
}