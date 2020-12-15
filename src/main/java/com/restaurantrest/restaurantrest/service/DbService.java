package com.restaurantrest.restaurantrest.service;

import com.restaurantrest.restaurantrest.dao.*;
import com.restaurantrest.restaurantrest.domain.*;
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

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private TempPragueDao tempPragueDao;

    public Cart saveCart(Cart cart) {
        return cartDao.save(cart);
    }

    public Optional<Cart> findCartById(Long cartId) {
        return cartDao.findById(cartId);
    }

    public Optional<User> findUserById(Long userId) {
        return userDao.findById(userId);
    }

    public Optional<Dish> findDishById(Long dishId) {
        return dishDao.findById(dishId);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public void removeUserById(Long id) {
        userDao.deleteById(id);
    }

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    public Optional<List<Order>> getOrdersByDate(LocalDate date) {
        return orderDao.findOrdersByOrderDate(date);
    }

    public Optional<Order> findOrderById(Long id) {
        return orderDao.findById(id);
    }

    public void removeOrderById(Long id) {
        orderDao.deleteById(id);
    }

    public List<Cart> getCarts() {
        return cartDao.findAll();
    }

    public void removeCartById(Long id) {
        cartDao.deleteById(id);
    }

    public List<Dish> getDishes() {
        return dishDao.findAll();
    }

    public void removeDishById(Long id) {
        dishDao.deleteById(id);
    }

    public Dish saveDish(Dish dish) {
        return dishDao.save(dish);
    }

    public Menu saveMenu(Menu menu) {
        return menuDao.save(menu);
    }

    public List<Menu> getMenus() {
        return menuDao.findAll();
    }

    public Optional<Menu> findMenuById(Long id) {
        return menuDao.findById(id);
    }

    public void removeMenuById(Long id) {
        menuDao.deleteById(id);
    }

    public Review saveReview(Review review) {
        return reviewDao.save(review);
    }

    public List<Review> getReviews() {
        return reviewDao.findAll();
    }

    public Optional<Review> findReviewById(Long id) {
        return reviewDao.findById(id);
    }

    public void removeReviewById(Long id) {
        reviewDao.deleteById(id);
    }

    public void saveTemp(Long id, LocalDate date, Double temp) {
        tempPragueDao.saveTemp(id, date, temp);
    }

    public List<Temp> getTemps() {
        return tempPragueDao.findAll();
    }

    public Temp findTempByDate(LocalDate date) {
        return tempPragueDao.findTempByDate(date);
    }

    public boolean removeTempById(Long id) {
        return tempPragueDao.deleteTemp(id);
    }
}
