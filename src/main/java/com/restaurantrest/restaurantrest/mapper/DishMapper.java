package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.CartNotFoundException;
import com.restaurantrest.restaurantrest.dao.CartDao;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DishMapper {

    @Autowired
    private CartDao cartDao;

    public Dish mapToDish(final DishDto dishDto) throws CartNotFoundException {
        return new Dish(
                dishDto.getDishId(),
                dishDto.getName(),
                dishDto.getPrice(),
                cartDao.findById(dishDto.getCartId()).orElseThrow(CartNotFoundException::new)
        );
    }

    public DishDto mapToDishDto(final Dish dish){
        return new DishDto(
                dish.getDishId(),
                dish.getName(),
                dish.getPrice(),
                dish.getCart().getCartId()
        );
    }

    public List<DishDto> mapToDishDtoList(final List<Dish> dishList) {
        return dishList.stream()
                .map(d -> new DishDto(d.getDishId(), d.getName(), d.getPrice(), d.getCart().getCartId()))
                .collect(Collectors.toList());
    }

    public List<Dish> mapToDishList(final List<DishDto> dishDtoList){
        return dishDtoList.stream()
                .map(d -> mapToDish(d))
                .collect(Collectors.toList());
    }
}
