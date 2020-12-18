package com.restaurantrest.restaurantrest.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderBuilder {
    private Long orderId;
    private LocalDate orderDate;
    private BigDecimal totalPrice;
    private User user;
    private Cart cart;

    public OrderBuilder setOrderId(Long orderId) {
        this.orderId = orderId;
        return  this;
    }

    public OrderBuilder setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return  this;
    }

    public OrderBuilder setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return  this;
    }

    public OrderBuilder setUser(User user) {
        this.user = user;
        return  this;
    }

    public OrderBuilder setCart(Cart cart) {
        this.cart = cart;
        return  this;
    }

    public Order createOrder(){
        return new Order(orderId, orderDate, totalPrice, user, cart);
    }
}
