package com.restaurantrest.restaurantrest.builder;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderBuilderTest {
    @Test
    public void shouldCreateOrder(){
        //Given
        Long id = 1L;
        LocalDate orderDate = LocalDate.of(2020, 12, 21);
        BigDecimal totalPrice = new BigDecimal("100");
        User user = new User();
        Cart cart = new Cart();

        //When
        Order order = new OrderBuilder().setOrderId(id).setOrderDate(orderDate).setTotalPrice(totalPrice)
                .setUser(user).setCart(cart).createOrder();

        //Then
        long orderId  = order.getOrderId();
        Assert.assertEquals(1L, orderId);
        Assert.assertEquals(LocalDate.of(2020, 12, 21), order.getOrderDate());
        Assert.assertEquals(new BigDecimal("100"), order.getTotalPrice());
    }
}