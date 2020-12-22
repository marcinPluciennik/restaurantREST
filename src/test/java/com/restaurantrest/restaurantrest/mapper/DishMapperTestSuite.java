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

    @Test
    public void testMapToDish(){
        //Given
        DishDto dishDto = new DishDto(1L,"Dish1", new BigDecimal("100"), new ArrayList<>());

        //When
        Dish dish = dishMapper.mapToDish(dishDto);

        //Then
        long id  = dish.getDishId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("Dish1", dish.getName());
        Assert.assertEquals(new BigDecimal(100), dish.getPrice());
    }

    @Test
    public void testMapToDishDtoList(){
        //Given
        Dish dish1 = new Dish(1L,"Dish1", new BigDecimal("100"), new ArrayList<>());
        Dish dish2 = new Dish(2L,"Dish2", new BigDecimal("100"), new ArrayList<>());
        Dish dish3 = new Dish(3L,"Dish3", new BigDecimal("100"), new ArrayList<>());
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);

        //When
        List<DishDto> dishDtoList = dishMapper.mapToDishDtoList(dishes);

        //Then
        long id  = dishDtoList.get(0).getDishId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("Dish1", dishDtoList.get(0).getName());
        Assert.assertEquals(new BigDecimal(100), dishDtoList.get(0).getPrice());
    }

    @Test
    public void testMapToDishList(){
        //Given
        DishDto dishDto1 = new DishDto(1L,"Dish1", new BigDecimal("100"), new ArrayList<>());
        DishDto dishDto2 = new DishDto(2L,"Dish2", new BigDecimal("100"), new ArrayList<>());
        DishDto dishDto3 = new DishDto(3L,"Dish3", new BigDecimal("100"), new ArrayList<>());
        List<DishDto> dishDtoList = new ArrayList<>();
        dishDtoList.add(dishDto1);
        dishDtoList.add(dishDto2);
        dishDtoList.add(dishDto3);

        //When
        List<Dish> dishList = dishMapper.mapToDishList(dishDtoList);

        //Then
        long id  = dishList.get(0).getDishId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("Dish1", dishList.get(0).getName());
        Assert.assertEquals(new BigDecimal(100), dishList.get(0).getPrice());
    }

    @Test
    public void testMapToCartsIdsList(){
        //Given
        Cart cart1 = new Cart(1L, new ArrayList<>());
        Cart cart2 = new Cart(2L, new ArrayList<>());
        Cart cart3 = new Cart(3L, new ArrayList<>());
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);
        cartList.add(cart3);

        //When
        List<Long> cartsIdsList = dishMapper.mapToCartsIdsList(cartList);

        //Then
        long id  = cartsIdsList.get(0);
        Assert.assertEquals(1L, id);
        Assert.assertEquals(3, cartsIdsList.size());
    }
}