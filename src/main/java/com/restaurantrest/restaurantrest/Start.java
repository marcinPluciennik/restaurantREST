package com.restaurantrest.restaurantrest;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import com.restaurantrest.restaurantrest.repository.CartRepo;
import com.restaurantrest.restaurantrest.repository.UserRepo;
import com.restaurantrest.restaurantrest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Start {

    @Autowired
    public Start(UserRepo userDao, CartRepo cartDao, OrderRepo orderDao) {

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cartDao.save(cart1);
        cartDao.save(cart2);

        Order order1 = new Order(10.40);
        order1.setCart(cart1);
        Order order2 = new Order(200.90);
        order2.setCart(cart2);

        orderDao.save(order1);
        orderDao.save(order2);

        User user1 = new User("Adam", "Kowalski", "123123123", "bla@gmail.com");
        user1.setOrder(order1);
        User user2 = new User("Wojtek", "Tarnowski", "345345345", "wt@gmail.com");
        user2.setOrder(order2);

        userDao.save(user1);
        userDao.save(user2);
    }
}
