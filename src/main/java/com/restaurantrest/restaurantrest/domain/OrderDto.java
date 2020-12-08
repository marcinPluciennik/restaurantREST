package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private LocalDate orderDate;
    private double totalPrice;
    private Long userId;
    private Long cartId;
}
