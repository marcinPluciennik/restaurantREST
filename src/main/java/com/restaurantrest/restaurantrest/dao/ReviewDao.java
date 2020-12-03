package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReviewDao extends JpaRepository<Review, Long> {
}
