package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.CartDto;
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
public class CartMapperTestSuite {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testMapToCartDto(){
        //Given
        Dish dish1 = new Dish("Dish1", new BigDecimal("100"));
        Dish dish2 = new Dish("Dish2", new BigDecimal("100"));
        Dish dish3 = new Dish("Dish3", new BigDecimal("100"));
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        Cart cart = new Cart(1L,dishes);

        //When
        CartDto cartDto = cartMapper.mapToCartDto(cart);

        //Then
        long id = cartDto.getCartId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(3, cartDto.getDishesIds().size());
    }

    @Test
    public void testMapToCart(){
        //Given
        DishDto dishDto1 = new DishDto(1L,"Dish1", new BigDecimal("100"));
        DishDto dishDto2 = new DishDto(2L,"Dish2", new BigDecimal("100"));
        DishDto dishDto3 = new DishDto(3L,"Dish3", new BigDecimal("100"));
        List<Long> dishIdsList = new ArrayList<>();
        dishIdsList.add(dishDto1.getDishId());
        dishIdsList.add(dishDto2.getDishId());
        dishIdsList.add(dishDto3.getDishId());

        CartDto cartDto = new CartDto(1L, dishIdsList);

        //When
        Cart cart = cartMapper.mapToCart(cartDto);

        //Then
        long id = cart.getCartId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(3, cart.getDishList().size());
    }

    @Test
    public void testMapToCartDtoList(){
        //Given
        Cart cart1 = new Cart(1L, new ArrayList<>());
        Cart cart2 = new Cart(2L, new ArrayList<>());
        Cart cart3 = new Cart(3L, new ArrayList<>());
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);
        cartList.add(cart3);

        //When
        List<CartDto> cartDtoList = cartMapper.mapToCartDtoList(cartList);

        //Then
        long id = cartDtoList.get(0).getCartId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(3, cartDtoList.size());
    }

    @Test
    public void testMapDishesIdsList(){
        //Given
        Dish dish1 = new Dish(1L,"Dish1", new BigDecimal("100"));
        Dish dish2 = new Dish(2L,"Dish2", new BigDecimal("100"));
        Dish dish3 = new Dish(3L,"Dish3", new BigDecimal("100"));
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);

        //When
        List<Long> dishesIdsList = cartMapper.mapToDishesIdsList(dishes);

        //Then
        long dishId = dishesIdsList.get(0);
        Assert.assertEquals(1L, dishId);
        Assert.assertEquals(3, dishesIdsList.size());
    }
}