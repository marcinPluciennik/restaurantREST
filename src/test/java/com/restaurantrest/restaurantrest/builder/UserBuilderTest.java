package com.restaurantrest.restaurantrest.builder;

import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserBuilderTest {
    @Test
    public void shouldCreateUser(){
        //Given
        Long id = 1L;
        String name = "John";
        String surname = "Smith";
        String phone = "000000000";
        String email = "foo@gmail.com";
        List<Order> orderList = new ArrayList<>();

        //When
        User user = new UserBuilder().setUserId(id).setName(name).setSurname(surname).setPhone(phone).setEmail(email)
                .setOrdersList(orderList).createUser();

        //Then
        long userId  = user.getUserId();
        Assert.assertEquals(1L, userId);
        Assert.assertEquals("John", user.getName());
        Assert.assertEquals("Smith", user.getSurname());
        Assert.assertEquals("000000000", user.getPhone());
        Assert.assertEquals("foo@gmail.com", user.getEmail());
        Assert.assertEquals(new ArrayList<>(), user.getOrdersList());
    }
}