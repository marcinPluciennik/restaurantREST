package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.domain.MyReviewDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MyReviewMapperTestSuite {

    @Autowired
    private MyReviewMapper myReviewMapper;

    @Test
    public void testMapToReviewDto(){
        //Given
        MyReview myReview = new MyReview(1L,"OK", 5);

        //When
        MyReviewDto myReviewDto = myReviewMapper.mapToReviewDto(myReview);

        //Then
        long id  = myReviewDto.getReviewId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("OK", myReviewDto.getReviewText());
        Assert.assertEquals(5, myReviewDto.getRating(),0);
    }
}