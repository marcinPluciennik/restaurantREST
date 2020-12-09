package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<Long> ordersIds;
}
