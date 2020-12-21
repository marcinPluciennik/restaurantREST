package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private LocalDate orderDate;
    private BigDecimal totalPrice;
    private Long userId;
    private Long cartId;

    public OrderDto(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
