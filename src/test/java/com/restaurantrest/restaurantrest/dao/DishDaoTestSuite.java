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

    @Test
    public void testSaveManyToMany(){
        //Given
        Dish dish1 = new Dish("Dish1", new BigDecimal("10"));
        Dish dish2 = new Dish("Dish2", new BigDecimal("20"));
        Dish dish3 = new Dish("Dish3", new BigDecimal("30"));
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cart1.getDishList().add(dish1);
        cart1.getDishList().add(dish1);
        cart1.getDishList().add(dish2);
        dish1.getCartList().add(cart1);
        dish1.getCartList().add(cart1);
        dish2.getCartList().add(cart1);

        cart2.getDishList().add(dish3);
        cart2.getDishList().add(dish2);
        dish3.getCartList().add(cart2);
        dish2.getCartList().add(cart2);

        //When
        dishDao.save(dish1);
        long dish1Id = dish1.getDishId();
        dishDao.save(dish2);
        long dish2Id = dish2.getDishId();
        dishDao.save(dish3);
        long dish3Id = dish3.getDishId();

        //Then
        Assert.assertNotEquals(0, dish1Id);
        Assert.assertNotEquals(0, dish2Id);
        Assert.assertNotEquals(0, dish3Id);
    }
}