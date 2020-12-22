package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.CartNotFoundException;
import com.restaurantrest.restaurantrest.dao.CartDao;
import com.restaurantrest.restaurantrest.domain.Cart;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DishMapper {

    @Autowired
    private CartDao cartDao;

    public Dish mapToDish(final DishDto dishDto) throws CartNotFoundException{
        return new Dish(
                dishDto.getDishId(),
                dishDto.getName(),
                dishDto.getPrice(),
                mapToCartList(dishDto.getCartsIds()));
    }

    public DishDto mapToDishDto(final Dish dish){
        return new DishDto(
                dish.getDishId(),
                dish.getName(),
                dish.getPrice(),
                mapToCartsIdsList(dish.getCartList()));
    }

    public List<DishDto> mapToDishDtoList(final List<Dish> dishList) {
        return dishList.stream()
                .map(d -> new DishDto(d.getDishId(), d.getName(), d.getPrice(),
                        mapToCartsIdsList(d.getCartList())))
                .collect(Collectors.toList());
    }

    public List<Dish> mapToDishList(final List<DishDto> dishDtoList){
        return dishDtoList.stream()
                .map(d -> mapToDish(d))
                .collect(Collectors.toList());
    }

    public List<Cart> mapToCartList(final List<Long> cartsIds) {
        return cartsIds.stream()
                .map(orderId -> cartDao.findById(orderId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Long> mapToCartsIdsList(final List<Cart> cartList) {
        return cartList.stream()
                .map(Cart::getCartId)
                .collect(Collectors.toList());
    }
}
