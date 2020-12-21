package com.restaurantrest.restaurantrest.conroller;

import com.google.gson.Gson;
import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.CartMapper;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartMapper cartMapper;

    @MockBean
    private DbService service;

    @MockBean
    private DishMapper dishMapper;

    @Test
    public void shouldFetchEmptyCartList() throws Exception{
        //Given
        List<CartDto> cartDtoList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();

        when(service.getCarts()).thenReturn(cartList);
        when(cartMapper.mapToCartDtoList(cartList)).thenReturn(cartDtoList);

        //When & Then
        mockMvc.perform(get("/carts/getCarts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchCartList() throws Exception{
        //Given
        List<CartDto> cartDtoList = new ArrayList<>();
        List<Long> doshIds = new ArrayList<>();
        CartDto cartDto = new CartDto(1L, doshIds);
        cartDtoList.add(cartDto);

        List<Cart> cartList = new ArrayList<>();
        List<Dish> dishes = new ArrayList<>();
        Cart cart = new Cart(1L, dishes);
        cartList.add(cart);

        when(service.getCarts()).thenReturn(cartList);
        when(cartMapper.mapToCartDtoList(cartList)).thenReturn(cartDtoList);

        //When & Then
        mockMvc.perform(get("/carts/getCarts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cartId", is(1)));
    }

    @Test
    public void shouldFetchCartById() throws Exception{
        //Given
        CartDto cartDto = new CartDto(1L, new ArrayList<>());
        Cart cart = new Cart(1L, new ArrayList<>());
        Optional<Cart> cartOptional = Optional.of(cart);
        Long cartId = cart.getCartId();

        when(service.findCartById(cartId)).thenReturn(cartOptional);
        when(cartMapper.mapToCartDto(cart)).thenReturn(cartDto);

        //When & Then
        mockMvc.perform(get("/carts/getCart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId", is(1)));
    }

    @Test
    public void shouldDeleteCartById() throws Exception{
        //Given
        List<Cart> cartList = new ArrayList<>();
        List<Dish> dishes = new ArrayList<>();
        Cart cart = new Cart(1L, dishes);
        cartList.add(cart);
        Long cartId = cart.getCartId();

        when(service.getCarts()).thenReturn(cartList);
        doNothing().when(service).removeCartById(cartId);

        //When & Then
        mockMvc.perform(delete("/carts/removeCart/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteDishFromCartById() throws Exception{
        //Given
        List<Dish> dishes = new ArrayList<>();
        Dish dish = new Dish(1L, "test1", new BigDecimal(100), new ArrayList<>());
        dishes.add(dish);

        List<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart(1L, dishes);
        cartList.add(cart);
        Long cartId = cart.getCartId();
        Optional<Cart> cartOptional = Optional.of(cart);
        CartDto cartDto = new CartDto(1L, new ArrayList<>());

        when(service.findCartById(cartId)).thenReturn(cartOptional);
        when(service.saveCart(cart)).thenReturn(cart);
        when(cartMapper.mapToCartDto(cart)).thenReturn(cartDto);

        //When & Then
        mockMvc.perform(delete("/carts/removeDish/1/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchDishListFromCart() throws Exception{
        //Given
        List<Cart> cartList = new ArrayList<>();
        List<Dish> dishes = new ArrayList<>();
        Dish dish1 = new Dish(1L, "test1", new BigDecimal(100), new ArrayList<>());
        Dish dish2 = new Dish(2L, "test2", new BigDecimal(200), new ArrayList<>());
        Dish dish3 = new Dish(3L, "test3", new BigDecimal(300), new ArrayList<>());
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        Cart cart = new Cart(1L, dishes);
        cartList.add(cart);
        List<DishDto> dishesDto = new ArrayList<>();
        DishDto dishDto1 = new DishDto(1L, "test1", new BigDecimal(100), new ArrayList<>());
        DishDto dishDto2 = new DishDto(2L, "test2", new BigDecimal(200), new ArrayList<>());
        DishDto dishDto3 = new DishDto(3L, "test3", new BigDecimal(300), new ArrayList<>());
        dishesDto.add(dishDto1);
        dishesDto.add(dishDto2);
        dishesDto.add(dishDto3);

        when(service.getCarts()).thenReturn(cartList);
        when(dishMapper.mapToDishDtoList(dishes)).thenReturn(dishesDto);

        //When & Then
        mockMvc.perform(get("/carts/getDishes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].dishId", is(1)))
                .andExpect(jsonPath("$[1].dishId", is(2)))
                .andExpect(jsonPath("$[2].dishId", is(3)));
    }

    @Test
    public void shouldCreateCart() throws Exception{
        //Given
        Cart cart = new Cart(1L, new ArrayList<>());
        CartDto cartDto = new CartDto(1L, new ArrayList<>());

        when(cartMapper.mapToCart(any(CartDto.class))).thenReturn(cart);
        when(service.saveCart(any(Cart.class))).thenReturn(cart);
        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartDto);

        //When & Then
        mockMvc.perform(post("/carts/createNewCart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.cartId", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddDishToCart() throws Exception{
        //Given
        List<Dish> dishes = new ArrayList<>();
        Cart cart = new Cart(1L, dishes);
        Long cartId = cart.getCartId();
        Optional<Cart> cartOptional = Optional.of(cart);

        Dish dish = new Dish(1L, "test1", new BigDecimal(100), new ArrayList<>());

        List<Long> dishesIds = new ArrayList<>();
        dishesIds.add(dish.getDishId());
        CartDto cartDto = new CartDto(1L, dishesIds);

        when(service.findCartById(cartId)).thenReturn(cartOptional);
        when(service.saveCart(any(Cart.class))).thenReturn(cart);
        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartDto);

        //When & Then
        cart.getDishList().add(dish);

        mockMvc.perform(put("/carts/addDish/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.cartId", is(1)))
                .andExpect(jsonPath("$.dishesIds", hasSize(1)))
                .andExpect(status().isOk());
    }
}