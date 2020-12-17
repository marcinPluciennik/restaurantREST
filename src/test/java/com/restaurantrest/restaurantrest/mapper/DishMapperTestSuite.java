package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.DishDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishMapperTestSuite {

    @Autowired
    private DishMapper dishMapper;

    @Test
    public void testMapToDishDto(){
        //Given

        Dish dish1 = new Dish("Dish1", new BigDecimal("100"));
        Dish dish2 = new Dish("Dish2", new BigDecimal("100"));
        Dish dish3 = new Dish("Dish3", new BigDecimal("100"));
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        Cart cart = new Cart(1L,dishes);
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);

        Dish dish = new Dish(1L,"NewDish", new BigDecimal(20), carts);

        //When
        DishDto dishDto = dishMapper.mapToDishDto(dish);

        //Then
        long id  = dishDto.getDishId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(1, dishDto.getCartsIds().size());
    }

}