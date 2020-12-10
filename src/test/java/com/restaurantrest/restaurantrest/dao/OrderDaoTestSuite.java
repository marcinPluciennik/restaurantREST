package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderDaoTestSuite {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testOrderDaoSave(){
        //Given
        Order order = new Order();
        Cart cart = new Cart();
        User user = new User();
        order.setCart(cart);
        order.setUser(user);

        //When
        orderDao.save(order);

        //Then
        long id = order.getOrderId();
        Optional<Order> orderDaoById = orderDao.findById(id);
        Assert.assertTrue(orderDaoById.isPresent());
    }
    @Test
    public void testOrderDaoSaveWithoutUserCart(){
        //Given
        Order order = new Order();

        //When
        orderDao.save(order);

        //Then
        long id = order.getOrderId();
        Optional<Order> orderDaoById = orderDao.findById(id);
        Assert.assertTrue(orderDaoById.isPresent());
    }
}