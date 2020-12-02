package com.restaurantrest.restaurantrest.repository;

import com.restaurantrest.restaurantrest.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
