package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Cart;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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
        Assert.assertEquals("jkowalski@gamil.com", userDto.getEmail());
        Assert.assertEquals(new ArrayList<>(), userDto.getOrdersIds());
    }

    @Test
    public void testMapToUser(){
        //Given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "111111111",
                "jkowalski@gamil.com", new ArrayList<>());

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        long id  = user.getUserId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("Jan", user.getName());
        Assert.assertEquals("Kowalski", user.getSurname());
        Assert.assertEquals("111111111", user.getPhone());
        Assert.assertEquals("jkowalski@gamil.com", userDto.getEmail());
        Assert.assertEquals(new ArrayList<>(), userDto.getOrdersIds());
    }

    @Test
    public void testMapToUserDtoList(){
        //Given
        User user1 = new User(1L, "Jan", "Kowalski", "111111111",
                "jkowalski@gamil.com", new ArrayList<>());
        User user2 = new User(2L, "John", "Smith", "222222222",
                "j@gamil.com", new ArrayList<>());
        User user3 = new User(3L, "Joe", "Brown", "333333333",
                "k@gamil.com", new ArrayList<>());
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        long id  = userDtoList.get(0).getUserId();
        Assert.assertEquals(1L, id);
        Assert.assertEquals("Jan", userDtoList.get(0).getName());
        Assert.assertEquals("Kowalski", userDtoList.get(0).getSurname());
        Assert.assertEquals("111111111", userDtoList.get(0).getPhone());
        Assert.assertEquals("jkowalski@gamil.com", userDtoList.get(0).getEmail());
        Assert.assertEquals(new ArrayList<>(), userDtoList.get(0).getOrdersIds());
    }

    @Test
    public void testMapToOrdersIdsList(){
        //Given
        User user = new User(1L,"Jan", "Nowak", "111111111",
                "jnowak@gmail.com", new ArrayList<>());
        Cart cart = new Cart(1L, new ArrayList<>());
        Order order = new Order(1L, LocalDate.of(2020,12,11),
                new BigDecimal(100), user, cart);
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);

        //When
        List<Long> ordersIdsList = userMapper.mapToOrdersIdsList(ordersList);

        //Then
        long id  = ordersIdsList.get(0);
        Assert.assertEquals(1L, id);
        Assert.assertEquals(1, ordersIdsList.size());
    }
}