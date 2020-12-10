package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
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
public class UserDaoTestSuite {

    @Autowired
    private UserDao userDao;

    @Test
    public void testUserDaoSave(){
        //Given
        User user = new User();
        Order order = new Order();
        user.getOrdersList().add(order);

        //Then
        userDao.save(user);

        //When
        long id = user.getUserId();
        Optional<User> userDaoById = userDao.findById(id);
        Assert.assertTrue(userDaoById.isPresent());
        Assert.assertEquals(1, user.getOrdersList().size());
    }

    @Test
    public void testUserDaoSaveWithoutOrder(){
        //Given
        User user = new User();

        //Then
        userDao.save(user);

        //When
        long id = user.getUserId();
        Optional<User> userDaoById = userDao.findById(id);
        Assert.assertTrue(userDaoById.isPresent());
        Assert.assertEquals(0, user.getOrdersList().size());
    }
}