package com.restaurantrest.restaurantrest.builder;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;

import java.util.ArrayList;
import java.util.List;

public class CartBuilder {
    private Long cartId;
    private List<Dish> dishList = new ArrayList<>();

    public CartBuilder setCartId(Long cartId) {
        this.cartId = cartId;
        return  this;
    }

    public CartBuilder setDishList(List<Dish> dishList) {
        this.dishList = dishList;
        return  this;
    }

    public Cart createCart(){
        return new Cart(cartId, dishList);
    }
}
