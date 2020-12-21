package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.client.RestaurantClient;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.DishDto;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DishController.class)
public class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantClient restaurantClient;

    @MockBean
    private DbService service;

    @MockBean
    private DishMapper dishMapper;

    @Test
    public void shouldFetchEmptyDishList() throws Exception {
        //Given
        List<DishDto> dishDtoList = new ArrayList<>();
        List<Dish> dishList = new ArrayList<>();

        when(service.getDishes()).thenReturn(dishList);
        when(dishMapper.mapToDishDtoList(dishList)).thenReturn(dishDtoList);

        //When & Then
        mockMvc.perform(get("/dishes/getDishes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchDishList() throws Exception {
        //Given
        List<Dish> dishList = new ArrayList<>();
        Dish dish1 = new Dish(1L, "test1", new BigDecimal(100), new ArrayList<>());
        Dish dish2 = new Dish(2L, "test2", new BigDecimal(200), new ArrayList<>());
        Dish dish3 = new Dish(3L, "test3", new BigDecimal(300), new ArrayList<>());
        dishList.add(dish1);
        dishList.add(dish2);
        dishList.add(dish3);

        List<DishDto> dishDtoList = new ArrayList<>();
        DishDto dishDto1 = new DishDto(1L, "test1", new BigDecimal(100), new ArrayList<>());
        DishDto dishDto2 = new DishDto(2L, "test2", new BigDecimal(200), new ArrayList<>());
        DishDto dishDto3 = new DishDto(3L, "test3", new BigDecimal(300), new ArrayList<>());
        dishDtoList.add(dishDto1);
        dishDtoList.add(dishDto2);
        dishDtoList.add(dishDto3);

        when(service.getDishes()).thenReturn(dishList);
        when(dishMapper.mapToDishDtoList(dishList)).thenReturn(dishDtoList);

        //When & Then
        mockMvc.perform(get("/dishes/getDishes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].dishId", is(1)))
                .andExpect(jsonPath("$[1].dishId", is(2)))
                .andExpect(jsonPath("$[2].dishId", is(3)))
                .andExpect(jsonPath("$[0].name", is("test1")))
                .andExpect(jsonPath("$[1].name", is("test2")))
                .andExpect(jsonPath("$[2].name", is("test3")));
    }

    @Test
    public void shouldFetchDishById() throws Exception {
        //Given
        DishDto dishDto = new DishDto(1L, "test", new BigDecimal(100), new ArrayList<>());
        Dish dish = new Dish(1L, "test", new BigDecimal(100), new ArrayList<>());
        Long dishId = dish.getDishId();

        when(service.findDishById(dishId)).thenReturn(dish);
        when(dishMapper.mapToDishDto(dish)).thenReturn(dishDto);

        //When & Then
        mockMvc.perform(get("/dishes/getDish/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dishId", is(1)))
                .andExpect(jsonPath("$.name", is("test")));
    }

    @Test
    public void shouldDeleteDishById() throws Exception{
        //Given
        //When & Then
        mockMvc.perform(delete("/dishes/removeDish/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}