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

    public Optional<List<Dish>> getDishesByCartId(Long cartId){
        return dishDao.findDishesByCart_CartId(cartId);
    }

    public Optional<Cart> findCartById(Long cartId){
        return cartDao.findById(cartId);
    }

    public Optional<User> findUserById(Long userId){
        return userDao.findById(userId);
    }

    public Optional<Dish> findDishById(Long dishId){
        return dishDao.findById(dishId);
    }

    public User saveUser(User user){
        return userDao.save(user);
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public void removeUserById(Long id){
        userDao.deleteById(id);
    }

}
