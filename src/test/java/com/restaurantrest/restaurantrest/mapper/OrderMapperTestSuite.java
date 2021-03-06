package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.CartNotFoundException;
import com.restaurantrest.restaurantrest.conroller.UserNotFoundException;
import com.restaurantrest.restaurantrest.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderMapperTestSuite {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testMapToOrderDto(){
        //Given
        Dish dish1 = new Dish("Dish1", new BigDecimal("100"));
        Dish dish2 = new Dish("Dish2", new BigDecimal("100"));
        Dish dish3 = new Dish("Dish3", new BigDecimal("100"));
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        Cart cart = new Cart(1L,dishes);
        List<Order> ordersList = new ArrayList<>();
        User user = new User(1L,"Jan", "Nowak", "111111111",
                "jnowak@gmail.com",ordersList);

        Order order = new Order(1L, LocalDate.of(2020,12,11),
                new BigDecimal(100), user, cart);

        //When
        OrderDto orderDto = orderMapper.mapToOrderDto(order);

        //Then
        long id  = orderDto.getOrderId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(new BigDecimal(100), orderDto.getTotalPrice());
    }

    @Test
    public void testMapToOrder() throws CartNotFoundException, UserNotFoundException{
        //Given
        User user = new User(1L,"Jan", "Nowak", "111111111",
                "jnowak@gmail.com", new ArrayList<>());
        Cart cart = new Cart(1L, new ArrayList<>());
        OrderDto orderDto = new OrderDto(1L, LocalDate.of(2020,12,11),
                new BigDecimal(100), user.getUserId(), cart.getCartId() );

        //When
        Order order = orderMapper.mapToOrder(orderDto);

        //Then
        long id  = order.getOrderId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(new BigDecimal(100), order.getTotalPrice());
    }

    @Test
    public void testMapToOrderDtoList(){
        //Given
        User user = new User(1L,"Jan", "Nowak", "111111111",
                "jnowak@gmail.com", new ArrayList<>());
        Cart cart = new Cart(1L, new ArrayList<>());
        Order order = new Order(1L, LocalDate.of(2020,12,11),
                new BigDecimal(100), user, cart);
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);


        //When
        List<OrderDto> orderDtoList = orderMapper.mapToOrderDtoList(ordersList);

        //Then
        long id  = orderDtoList.get(0).getOrderId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(new BigDecimal(100), orderDtoList.get(0).getTotalPrice());
    }
}