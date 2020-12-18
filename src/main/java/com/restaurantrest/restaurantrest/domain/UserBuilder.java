package com.restaurantrest.restaurantrest.domain;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    private Long userId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<Order> ordersList = new ArrayList<>();

    public UserBuilder setUserId(Long userId) {
        this.userId = userId;
        return  this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return  this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return  this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return  this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return  this;
    }

    public UserBuilder setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
        return  this;
    }

    public User createUser(){
        return new User(userId,name,surname,phone,email,ordersList);
    }
}
