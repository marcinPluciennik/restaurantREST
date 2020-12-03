package com.restaurantrest.restaurantrest.service;

import com.restaurantrest.restaurantrest.dao.CartDao;
import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.dao.UserDao;
import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private UserDao userDao;

    public Cart saveCart(Cart cart) {
        return cartDao.save(cart);
    }

    public Optional<List<Dish>> getDishesByCartId(long cartId){
        return dishDao.findDishesByCart_CartId(cartId);
    }

    public Optional<Cart> findCartById(long cartId){
        return cartDao.findById(cartId);
    }

    public Optional<User> findUserById(long userId){
        return userDao.findById(userId);
    }

    public Optional<Dish> findDishById(long dishId){
        return dishDao.findById(dishId);
    }
}
