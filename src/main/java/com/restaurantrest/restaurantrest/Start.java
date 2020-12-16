package com.restaurantrest.restaurantrest;

import com.restaurantrest.restaurantrest.conroller.MyReviewController;
import com.restaurantrest.restaurantrest.conroller.TempController;
import com.restaurantrest.restaurantrest.dao.*;
import com.restaurantrest.restaurantrest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;


@Component
public class Start {

    @Autowired
    public Start(UserDao userDao, CartDao cartDao, OrderDao orderDao, DishDao dishDao, MenuDao menuDao,
                 MyReviewDao myReviewDao, TempController tempController, MyReviewController myReviewController) {


        myReviewController.saveExistingReviews();

        tempController.saveTemp();


        Cart cart1 = new Cart();
        Cart cart2 = new Cart();


        Dish dish1 = new Dish("Schabowy", new BigDecimal("14.5"));
        Dish dish2 = new Dish("Ziemniaki", new BigDecimal("4.5"));


        Menu menu1 = new Menu("Salatki",
                LocalDate.of(2020, Month.DECEMBER, 2));
        Menu menu2 = new Menu("Desery",
                LocalDate.of(2020, Month.DECEMBER, 2));

        menuDao.save(menu1);
        menuDao.save(menu2);

        dish1.getCartList().add(cart1);
        dish2.getCartList().add(cart2);
        cart1.getDishList().add(dish1);
        cart2.getDishList().add(dish2);

        dish1.setMenu(menu1);
        dish2.setMenu(menu1);


        cartDao.save(cart1);
        cartDao.save(cart2);

        dishDao.save(dish1);
        dishDao.save(dish2);


        User user1 = new User("Adam", "Kowalski", "123123123", "bla@gmail.com");
        User user2 = new User("Wojtek", "Tarnowski", "345345345", "wt@gmail.com");

        userDao.save(user1);
        userDao.save(user2);


        Order order1 = new Order(new BigDecimal(10.40));
        order1.setCart(cart1);
        order1.setUser(user1);
        Order order2 = new Order(new BigDecimal(200.90));
        order2.setCart(cart2);
        order2.setUser(user2);

        orderDao.save(order1);
        orderDao.save(order2);

    }
}
