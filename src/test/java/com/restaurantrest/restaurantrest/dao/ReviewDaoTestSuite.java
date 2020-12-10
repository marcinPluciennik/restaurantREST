package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Review;
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
public class ReviewDaoTestSuite {

    @Autowired
    private ReviewDao reviewDao;

    @Test
    public void testReviewDaoSave(){
        //Given
        Review review = new Review("OK", 5);

        //When
        reviewDao.save(review);

        //Then
        long id = review.getReviewId();
        Optional<Review> reviewDaoById = reviewDao.findById(id);
        Assert.assertTrue(reviewDaoById.isPresent());
        Assert.assertEquals("OK", reviewDaoById.get().getReviewText());
        Assert.assertEquals(5, reviewDaoById.get().getRating(), 0);
    }

}