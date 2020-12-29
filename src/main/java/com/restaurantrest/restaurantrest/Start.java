package com.restaurantrest.restaurantrest;

import com.restaurantrest.restaurantrest.builder.CartBuilder;
import com.restaurantrest.restaurantrest.builder.OrderBuilder;
import com.restaurantrest.restaurantrest.builder.UserBuilder;
import com.restaurantrest.restaurantrest.conroller.DishController;
import com.restaurantrest.restaurantrest.conroller.MyReviewController;
import com.restaurantrest.restaurantrest.dao.*;
import com.restaurantrest.restaurantrest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class Start {

    @Autowired
    public Start(UserDao userDao, CartDao cartDao, OrderDao orderDao, MyReviewController myReviewController,
                 DishController dishController) {

        myReviewController.saveExistingReviews();
        dishController.saveExistingDishes();

        Cart cart1 = new CartBuilder().createCart();
        Cart cart2 = new CartBuilder().createCart();

        cartDao.save(cart1);
        cartDao.save(cart2);

        User user1 = new UserBuilder().setName("Adam").setSurname("Kowalski").setPhone("123123123")
                .setEmail("bla@gmail.com").createUser();
        User user2 = new UserBuilder().setName("Wojtek").setSurname("Tarnowski").setPhone("345345345")
                .setEmail("wt@gmail.com").createUser();
        userDao.save(user1);
        userDao.save(user2);

        Order order1 = new OrderBuilder().setTotalPrice(new BigDecimal(10.40)).createOrder();
        order1.setCart(cart1);
        order1.setUser(user1);
        Order order2 = new OrderBuilder().setTotalPrice(new BigDecimal(200.40)).createOrder();
        order2.setCart(cart2);
        order2.setUser(user2);

        orderDao.save(order1);
        orderDao.save(order2);
    }
}
