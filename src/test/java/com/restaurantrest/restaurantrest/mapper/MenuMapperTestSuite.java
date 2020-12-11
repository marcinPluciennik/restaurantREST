package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.Menu;
import com.restaurantrest.restaurantrest.domain.MenuDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuMapperTestSuite {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testMapToMenuDto(){
        //Given
        Dish dish1 = new Dish("Dish1", new BigDecimal("100"));
        Dish dish2 = new Dish("Dish2", new BigDecimal("100"));
        Dish dish3 = new Dish("Dish3", new BigDecimal("100"));
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        Menu menu = new Menu(1L,"Desserts",
                LocalDateTime.of(2020, 12, 11, 12, 12,12), dishes);

        //When
        MenuDto menuDto = menuMapper.mapToMenuDto(menu);

        //Then
        long id  = menuDto.getMenuId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals(3, menuDto.getDishesIds().size());
        Assert.assertEquals("Desserts", menuDto.getMenuName());
    }
}