package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Review;
import com.restaurantrest.restaurantrest.domain.ReviewDto;
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
public class ReviewMapperTestSuite {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    public void testMapToReviewDto(){
        //Given
        Review review = new Review(1L,"OK", 5);

        //When
        ReviewDto reviewDto = reviewMapper.mapToReviewDto(review);

        //Then
        long id  = reviewDto.getReviewId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("OK", reviewDto.getReviewText());
        Assert.assertEquals(5, reviewDto.getRating(),0);
    }
}