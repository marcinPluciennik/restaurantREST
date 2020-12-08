package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.CartMapper;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private DbService service;

    @Autowired
    private DishMapper dishMapper;


    @RequestMapping(method = RequestMethod.POST, value = "createCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return cartMapper.mapToCartDto(service.saveCart(cartMapper.mapToCart(cartDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addDish", consumes = APPLICATION_JSON_VALUE)
    public CartDto addDishToCart(@RequestParam Long cartId, @RequestParam Long dishId)
            throws CartNotFoundException, DishNotFoundException {
        Cart cart = service.findCartById(cartId).orElseThrow(CartNotFoundException::new);
        Dish dish = service.findDishById(dishId).orElseThrow(DishNotFoundException::new);
        cart.getDishList().add(dish);
        return cartMapper.mapToCartDto(service.saveCart(cart));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDishes")
    public List<DishDto> getDishesFromCart(@RequestParam Long cartId) throws DihsesListNotFoundException {
        List<Dish> dishes = service.getDishesByCartId(cartId).orElseThrow(DihsesListNotFoundException::new);
        return dishMapper.mapToDishDtoList(dishes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteDish")
    public CartDto deleteDishFromCart(@RequestParam Long dishId, @RequestParam Long cartId)
            throws CartNotFoundException {
        Cart cart = service.findCartById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getDishList().removeIf(dish -> dish.getDishId() == dishId);
        return cartMapper.mapToCartDto(service.saveCart(cart));
    }
}
