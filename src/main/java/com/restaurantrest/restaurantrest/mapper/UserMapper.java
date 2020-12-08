package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.OrderNotFoundException;
import com.restaurantrest.restaurantrest.domain.User;
import com.restaurantrest.restaurantrest.domain.UserDto;
import com.restaurantrest.restaurantrest.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private OrderDao orderRepo;

    public User mapToUser(final UserDto userDto) throws OrderNotFoundException {
        return new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getDate(),
                orderRepo.findById(userDto.getOrderId()).orElseThrow(OrderNotFoundException::new)
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                user.getDate(),
                user.getOrder().getOrderId()
        );
    }

    public List<UserDto> mapToUserDtoList(List<User> userList){
        return userList.stream()
                .map(u -> new UserDto(u.getUserId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(),
                        u.getDate(), u.getOrder().getOrderId()))
                .collect(Collectors.toList());
    }
}
