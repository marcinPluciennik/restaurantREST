package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.CartMapper;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @RequestMapping(method = RequestMethod.GET, value = "getCarts")
    public List<CartDto> getCarts(){
        List<Cart> carts = service.getCarts();
        return cartMapper.mapToCartDtoList(carts);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCart/{cartId}")
    public CartDto getCartById(@PathVariable Long cartId) throws CartNotFoundException{
        return cartMapper.mapToCartDto(service.findCartById(cartId).orElseThrow(CartNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createNewCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return cartMapper.mapToCartDto(service.saveCart(cartMapper.mapToCart(cartDto)));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "removeCart/{cartId}")
    public void removeCartById(@PathVariable Long cartId) {
        Optional<Cart> cartById = service.getCarts().stream()
                .filter(user -> user.getCartId() == cartId)
                .findFirst();
        if (cartById.isPresent()) {
            service.removeCartById(cartId);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDishes/{cartId}")
    public List<DishDto> getDishesFromCart(@PathVariable Long cartId){
        Optional<Cart> cartById = service.getCarts().stream()
                .filter(user -> user.getCartId() == cartId)
                .findFirst();
        if (cartById.isPresent()) {
            Cart cart = cartById.get();
            List<Dish> dishesFromCart = cart.getDishList();
            return dishMapper.mapToDishDtoList(dishesFromCart);
        }
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addDish/{cartId}", consumes = APPLICATION_JSON_VALUE)
    public CartDto addDishToCart(@PathVariable Long cartId, @RequestBody Dish dish)
            throws CartNotFoundException{
        Cart cart = service.findCartById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getDishList().add(dish);
        return cartMapper.mapToCartDto(service.saveCart(cart));
    }
}
