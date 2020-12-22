package com.restaurantrest.restaurantrest.conroller;


import com.google.gson.Gson;
import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.UserMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldFetchEmptyUserList() throws Exception{
        //Given
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        when(service.getUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When & Then
        mockMvc.perform(get("/users/getUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchUserList() throws Exception{
        //Given
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        userDtoList.add(userDto);

        List<User> userList = new ArrayList<>();
        User user = new User(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        userList.add(user);

        when(service.getUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When & Then
        mockMvc.perform(get("/users/getUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].phone", is("111111111")))
                .andExpect(jsonPath("$[0].email", is("test@gmail.com")));
    }

    @Test
    public void shouldFetchUserById() throws Exception{
        //Given
        UserDto userDto = new UserDto(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        User user = new User(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        Optional<User> userOptional = Optional.of(user);
        Long userId = user.getUserId();

        when(service.findUserById(userId)).thenReturn(userOptional);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/users/getUser/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.surname", is("Smith")))
                .andExpect(jsonPath("$.phone", is("111111111")))
                .andExpect(jsonPath("$.email", is("test@gmail.com")));
    }

    @Test
    public void shouldDeleteUserById() throws Exception{
        //Given
        User user = new User(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user);
        Long userId = user.getUserId();

        when(service.getUsers()).thenReturn(users);
        doNothing().when(service).removeOrderById(userId);

        //When & Then
        mockMvc.perform(delete("/users/removeUser/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateUser() throws Exception{
        //Given
        UserDto userDto = new UserDto(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        User user = new User(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());

        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(service.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/users/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.surname", is("Smith")))
                .andExpect(jsonPath("$.phone", is("111111111")))
                .andExpect(jsonPath("$.email", is("test@gmail.com")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditUser() throws Exception{
        //Given
        UserDto userDto = new UserDto(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        User user = new User(1L, "John", "Smith", "111111111",
                "test@gmail.com", new ArrayList<>());
        Optional<User> optionalUser = Optional.of(user);
        Long userId = userDto.getUserId();

        when(service.findUserById(userId)).thenReturn(optionalUser);
        doNothing().when(service).removeUserById(userId);
        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(service.saveUser(user)).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(put("/users/editUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}