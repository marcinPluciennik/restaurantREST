package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.User;
import com.restaurantrest.restaurantrest.domain.UserDto;
import com.restaurantrest.restaurantrest.mapper.UserMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto, Observable observable){
        observable.notify();
        return userMapper.mapToUserDto(service.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers(){
        List<User> users = service.getUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException{
        return userMapper.mapToUserDto(service.findUserById(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeUser/{userId}")
    public void removeUserById(@PathVariable Long userId) {
        Optional<User> userById = service.getUsers().stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst();
        if (userById.isPresent()) {
            service.removeUserById(userId);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editUser")
    public void editUserById(@RequestBody UserDto userDto){
        Optional<User> userById = service.getUsers().stream()
                .filter(user -> user.getUserId() == userDto.getUserId())
                .findFirst();
        if (userById.isPresent()) {
            service.removeUserById(userDto.getUserId());
            service.saveUser(userMapper.mapToUser(userDto));
        }
    }
}
