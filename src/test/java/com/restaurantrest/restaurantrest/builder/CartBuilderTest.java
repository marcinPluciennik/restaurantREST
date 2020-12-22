package com.restaurantrest.restaurantrest.builder;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartBuilderTest {

     @Test
    public void shouldCreateCart(){
         //Given
         Long id = 1L;
         Dish dish = new Dish("Dish", new BigDecimal("100"));
         List<Dish> dishList = new ArrayList<>();
         dishList.add(dish);

         //When
         Cart cart = new CartBuilder().setCartId(id).setDishList(dishList).createCart();

         //Then
         long cartId  = cart.getCartId();
         Assert.assertEquals(1L, cartId);
         Assert.assertEquals(1, cart.getDishList().size());
         Assert.assertEquals(new BigDecimal("100"), cart.getDishList().get(0).getPrice());
     }
}