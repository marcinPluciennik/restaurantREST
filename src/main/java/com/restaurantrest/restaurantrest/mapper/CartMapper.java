package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.CartDto;
import com.restaurantrest.restaurantrest.domain.Dish;
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
                mapToDishes(cartDto.getDishesIds())
        );
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getCartId(),
                mapToDishesIds(cart.getDishList())
        );
    }

    public List<Dish> mapToDishes(final List<Long> dishessIds) {
        return dishessIds.stream()
                .map(dishId -> dishDao.findById(dishId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Long> mapToDishesIds(final List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getDishId)
                .collect(Collectors.toList());
    }
}
