package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

    private Long dishId;
    private String name;
    private BigDecimal price;
    private List<Long> cartsIds;
    private Long menuId;
}
