package com.restaurantrest.restaurantrest.service;

import com.restaurantrest.restaurantrest.builder.CartBuilder;
import com.restaurantrest.restaurantrest.domain.Cart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DbServiceTest {

    @Autowired
    private DbService service;

    @Test
    public void shouldSaveCart(){
        //Given
        Cart cart = new CartBuilder().setCartId(1L).setDishList(new ArrayList<>()).createCart();

        //When
        Cart savedCart = service.saveCart(cart);

        //Then
        long id = savedCart.getCartId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(new ArrayList<>(), savedCart.getDishList());
    }

    @Test
    public void shouldSFetchCartById(){
        //Given
        Cart cart = new CartBuilder().setCartId(1L).setDishList(new ArrayList<>()).createCart();

        //When
        Optional<Cart> cartById = service.findCartById(cart.getCartId());

        //Then
        Assert.assertTrue(cartById.isPresent());
        Assert.assertEquals(1L, cartById.get().getCartId(), 0);
    }

    @Test
    public void shouldSFetchCartList(){
        //Given
        //When
        List<Cart> cartList = service.getCarts();

        //Then
        Assert.assertEquals(2, cartList.size());
    }
}