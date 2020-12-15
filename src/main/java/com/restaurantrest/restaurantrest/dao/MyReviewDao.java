package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.MyReview;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MyReviewDao {
    void save(String reviewText, int rating);
    void saveExistingReviews(List<MyReview> reviews);
    void updateMyReview(MyReview newMyReview);
    List<MyReview> findAll();
    boolean deleteMyReviewById(long id);
    MyReview findMyReviewById(long id);
}
