package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private DishDao dishDao;

    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getCartId(),
                mapToDishesList(cartDto.getDishesIds()));
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getCartId(),
                mapToDishesIdsList(cart.getDishList()));
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList){
        return cartList.stream()
                .map(c -> new CartDto(c.getCartId(), mapToDishesIdsList(c.getDishList())))
                .collect(Collectors.toList());
    }

    public List<Dish> mapToDishesList(final List<Long> dishesIds) {
        return dishesIds.stream()
                .map(dishId -> dishDao.findById(dishId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Long> mapToDishesIdsList(final List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getDishId)
                .collect(Collectors.toList());
    }
}
