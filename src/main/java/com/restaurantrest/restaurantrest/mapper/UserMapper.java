package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.User;
import com.restaurantrest.restaurantrest.domain.UserDto;
import com.restaurantrest.restaurantrest.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private OrderDao orderDao;

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPhone(),
                userDto.getEmail(),
                mapToOrders(userDto.getOrdersIds())
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                mapToOrdersIds(user.getOrdersList())
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(u -> new UserDto(u.getUserId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(),
                        mapToOrdersIds(u.getOrdersList())))
                .collect(Collectors.toList());
    }

    public List<Order> mapToOrders(final List<Long> ordersIds) {
        return ordersIds.stream()
                .map(orderId -> orderDao.findById(orderId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Long> mapToOrdersIds(final List<Order> orderList) {
        return orderList.stream()
                .map(Order::getOrderId)
                .collect(Collectors.toList());
    }

}
