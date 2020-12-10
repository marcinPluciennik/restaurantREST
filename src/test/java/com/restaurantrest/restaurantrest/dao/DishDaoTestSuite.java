package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.Menu;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishDaoTestSuite {

    @Autowired
    private DishDao dishDao;

    @Test
    public void testDishDaoSave(){
        //Given
        Dish dish = new Dish("Dish", new BigDecimal("100"));
        Cart cart = new Cart();
        Menu menu = new Menu();

        dish.getCartList().add(cart);
        dish.setMenu(menu);

        //When
        dishDao.save(dish);

        //Then
        long id = dish.getDishId();
        Optional<Dish> dishDaoById = dishDao.findById(id);
        Assert.assertTrue(dishDaoById.isPresent());
        Assert.assertEquals(1, dishDaoById.get().getCartList().size());
    }

    @Test
    public void testDishDaoSaveWithoutCartMenu(){
        //Given
        Dish dish = new Dish();

        //When
        dishDao.save(dish);

        //Then
        long id = dish.getDishId();
        Optional<Dish> dishDaoById = dishDao.findById(id);
        Assert.assertTrue(dishDaoById.isPresent());
        Assert.assertEquals(0, dish.getCartList().size());
    }

}