package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.User;
import com.restaurantrest.restaurantrest.domain.UserDto;
import com.restaurantrest.restaurantrest.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private OrderRepo orderRepo;

    public User mapToUser(UserDto userDto) throws OrderNotFoundException{
        return new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getDate(),
                orderRepo.findById(userDto.getOrderId()).orElseThrow(OrderNotFoundException::new));
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getPhone(),
                user.getEmail(),
                user.getDate(),
                user.getOrder().getOrderId());
    }
}
