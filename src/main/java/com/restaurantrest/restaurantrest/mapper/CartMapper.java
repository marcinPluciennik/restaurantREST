package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public Cart mapToCart(CartDto cartDto){
        return new Cart(cartDto.getCartId());
    }

    public CartDto mapToCartDto(Cart cart){
        return new CartDto(cart.getCartId());
    }
}
