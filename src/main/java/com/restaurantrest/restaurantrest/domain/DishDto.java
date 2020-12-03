package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

    private Long dishId;
    private String name;
    private BigDecimal price;
    private Long cartId;
}
