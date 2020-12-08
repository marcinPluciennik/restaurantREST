package com.restaurantrest.restaurantrest;

import com.restaurantrest.restaurantrest.dao.*;
import com.restaurantrest.restaurantrest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;


@Component
public class Start {

    @Autowired
    public Start(UserDao userDao, CartDao cartDao, OrderDao orderDao, DishDao dishDao, MenuDao menuDao,
                 ReviewDao reviewDao, TempDao tempDao) {

        Review review1 = new Review("OK!!!!", 5);
        Review review2 = new Review("SUPER", 4.5);
        reviewDao.save(review1);
        reviewDao.save(review2);

        Temp temp = new Temp(2);
        tempDao.save(temp);


        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cartDao.save(cart1);
        cartDao.save(cart2);

        Dish dish1 = new Dish("Schabowy", new BigDecimal("14.5"));
        dish1.setCart(cart2);
        Dish dish2 = new Dish("Ziemniaki", new BigDecimal("4.5"));
        dish2.setCart(cart2);
        Dish dish3 = new Dish("Surówka", new BigDecimal("4.5"));
        dish3.setCart(cart1);

        dishDao.save(dish1);
        dishDao.save(dish2);
        dishDao.save(dish3);

        Menu menu1 = new Menu("Salatki",
                LocalDateTime.of(2020, Month.DECEMBER, 2, 20,10,11));
        menu1.setDish(dish1);
        Menu menu2 = new Menu("Desery",
                LocalDateTime.of(2020, Month.DECEMBER, 2, 20,10,11));
        menu2.setDish(dish2);


        menuDao.save(menu1);
        menuDao.save(menu2);

        User user1 = new User("Adam", "Kowalski", "123123123", "bla@gmail.com");

        User user2 = new User("Wojtek", "Tarnowski", "345345345", "wt@gmail.com");

        userDao.save(user1);
        userDao.save(user2);


        Order order1 = new Order(10.40);
        order1.setCart(cart1);
        order1.setUser(user1);
        Order order2 = new Order(200.90);
        order2.setCart(cart2);
        order2.setUser(user2);

        orderDao.save(order1);
        orderDao.save(order2);



    }
}
