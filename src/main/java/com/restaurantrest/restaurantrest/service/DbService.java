package com.restaurantrest.restaurantrest.service;

import com.restaurantrest.restaurantrest.dao.CartDao;
import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.dao.OrderDao;
import com.restaurantrest.restaurantrest.dao.UserDao;
import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private OrderDao orderDao;

    public Cart saveCart(Cart cart) {
        return cartDao.save(cart);
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

    public Order saveOrder(Order order){
        return orderDao.save(order);
    }

    public List<Order> getOrders(){
        return orderDao.findAll();
    }

    public Optional<List<Order>> getOrdersByDate(LocalDate date){
        return orderDao.findOrdersByOrderDate(date);
    }

    public Optional<Order> findOrderbyId(Long id){
        return orderDao.findById(id);
    }

    public void removeOrderById(Long id){
        orderDao.deleteById(id);
    }

    public List<Cart> getCarts(){
        return cartDao.findAll();
    }

    public void removeCartById(Long id){
        cartDao.deleteById(id);
    }

    public List<Dish> getDishes(){
        return dishDao.findAll();
    }

    public void removeDishById(Long id){
        dishDao.deleteById(id);
    }

    public Dish saveDish(Dish dish) {
        return dishDao.save(dish);
    }
}
