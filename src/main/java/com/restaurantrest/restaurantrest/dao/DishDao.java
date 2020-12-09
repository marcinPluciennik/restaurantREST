package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DishDao extends JpaRepository<Dish, Long> {

    //Optional<List<Dish>> findDishesByCart_CartId(long cartId);
}
