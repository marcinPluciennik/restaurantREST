package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.OrderDto;
import com.restaurantrest.restaurantrest.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private CartRepo cartRepo;

    public Order mapToOrder(OrderDto orderDto) throws CartNotFoundException {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getOrderDate(),
                orderDto.getTotalPrice(),
                cartRepo.findById(orderDto.getCartId()).orElseThrow(CartNotFoundException::new));
    }

    public OrderDto mapToOrderDto(Order order){
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getOrderId());
    }
}
