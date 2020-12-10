package com.restaurantrest.restaurantrest.dao;


import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.Menu;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuDaoTestSuite {

    @Autowired
    private MenuDao menuDao;

    @Test
    public void testMenuDaoSave(){
        //Given
        Menu menu = new Menu();
        Dish dish = new Dish();
        menu.getDishesList().add(dish);

        //Then
        menuDao.save(menu);

        //When
        long id = menu.getMenuId();
        Optional<Menu> menuDaoById = menuDao.findById(id);
        Assert.assertTrue(menuDaoById.isPresent());
        Assert.assertEquals(1, menu.getDishesList().size());
    }

    @Test
    public void testMenuDaoSaveWithoutDish(){
        //Given
        Menu menu = new Menu();

        //Then
        menuDao.save(menu);

        //When
        long id = menu.getMenuId();
        Optional<Menu> menuDaoById = menuDao.findById(id);
        Assert.assertTrue(menuDaoById.isPresent());
        Assert.assertEquals(0, menu.getDishesList().size());
    }

}