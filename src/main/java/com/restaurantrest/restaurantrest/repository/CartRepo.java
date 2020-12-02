package com.restaurantrest.restaurantrest.repository;

import com.restaurantrest.restaurantrest.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{
}
