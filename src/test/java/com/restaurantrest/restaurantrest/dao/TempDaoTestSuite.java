package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Temp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TempDaoTestSuite {

    @Autowired
    private TempDao tempDao;

    @Test
    public void testTempDaoSave(){
        //Given
        Temp temp = new Temp(5);

        //When
        tempDao.save(temp);

        //Then
        long id = temp.getTempId();
        Optional<Temp> tempDaoById = tempDao.findById(id);
        Assert.assertTrue(tempDaoById.isPresent());
        Assert.assertEquals(5, tempDaoById.get().getTemp(), 0);
    }
}