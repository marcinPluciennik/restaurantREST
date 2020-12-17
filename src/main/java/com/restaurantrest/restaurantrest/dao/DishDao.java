package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.MyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface DishDao {

    Dish findDishById(long id);
    List<Dish> findAll();
    void saveExistingDishes(List<Dish> dishes);
    void save(String name, BigDecimal price);
    void updateDish(Dish newDish);
    boolean deleteDishById(long id);
}
