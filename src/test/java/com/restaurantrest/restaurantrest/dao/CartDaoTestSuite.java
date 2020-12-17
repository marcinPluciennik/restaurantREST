package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import org.junit.Assert;

import org.junit.Test;
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
public class CartDaoTestSuite {

    @Autowired
    private CartDao cartDao;

    @Test
    public void testCartDaoSave(){
        //Given
        Cart cart = new Cart();
        Dish dish1 = new Dish("Dish1", new BigDecimal("100"));
        Dish dish2 = new Dish("Dish2", new BigDecimal("200"));
        cart.getDishList().add(dish1);
        cart.getDishList().add(dish2);

        //When
        cartDao.save(cart);

        //Then
        long id = cart.getCartId();
        Optional<Cart> cartDaoById = cartDao.findById(id);
        Assert.assertTrue(cartDaoById.isPresent());
        Assert.assertEquals(2, cartDaoById.get().getDishList().size());
        Assert.assertEquals("Dish1", cartDaoById.get().getDishList().get(0).getName());
        Assert.assertEquals("Dish2", cartDaoById.get().getDishList().get(1).getName());
        Assert.assertEquals(BigDecimal.valueOf(100), cartDaoById.get().getDishList().get(0).getPrice());
        Assert.assertEquals(BigDecimal.valueOf(200), cartDaoById.get().getDishList().get(1).getPrice());

    }

    @Test
    public void testCartDaoSaveWithoutDishes(){
        //Given
        Cart cart = new Cart();

        //When
        cartDao.save(cart);

        //Then
        long id = cart.getCartId();

        Optional<Cart> cartDaoById = cartDao.findById(id);
        Assert.assertTrue(cartDaoById.isPresent());
        Assert.assertEquals(0, cartDaoById.get().getDishList().size());
    }

}