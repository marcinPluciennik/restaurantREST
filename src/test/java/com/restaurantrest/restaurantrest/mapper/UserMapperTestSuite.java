package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import com.restaurantrest.restaurantrest.domain.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapToUserDto(){
        //Given
        List<Order> ordersList = new ArrayList<>();
        User user = new User(1L, "Jan", "Kowalski", "111111111",
                "jkowalski@gamil.com", ordersList);

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        long id  = userDto.getUserId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("Jan", userDto.getName());
        Assert.assertEquals("Kowalski", userDto.getSurname());
        Assert.assertEquals("111111111", userDto.getPhone());
    }
}