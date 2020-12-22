package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.domain.MyReviewDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testMapToReview(){
        //Given
        MyReviewDto myReviewDto = new MyReviewDto(1L,"OK", 5);

        //When
        MyReview myReview = myReviewMapper.mapToReview(myReviewDto);

        //Then
        long id  = myReview.getReviewId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("OK", myReview.getReviewText());
        Assert.assertEquals(5, myReview.getRating(),0);
    }

    @Test
    public void testMapToReviewDtoList(){
        //Given
        MyReview myReview1 = new MyReview(1L,"OK", 5);
        MyReview myReview2 = new MyReview(2L,"OK", 4);
        MyReview myReview3 = new MyReview(3L,"OK", 3);
        List<MyReview> myReviewList = new ArrayList<>();
        myReviewList.add(myReview1);
        myReviewList.add(myReview2);
        myReviewList.add(myReview3);

        //When
        List<MyReviewDto> myReviewDtoList = myReviewMapper.mapToReviewDtoList(myReviewList);

        //Then
        long id  = myReviewDtoList.get(0).getReviewId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("OK", myReviewDtoList.get(0).getReviewText());
        Assert.assertEquals(5, myReviewDtoList.get(0).getRating(),0);
    }
}