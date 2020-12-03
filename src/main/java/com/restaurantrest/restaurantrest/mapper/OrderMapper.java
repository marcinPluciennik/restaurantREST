package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.CartNotFoundException;
import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.OrderDto;
import com.restaurantrest.restaurantrest.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private CartDao cartRepo;

    public Order mapToOrder(final OrderDto orderDto) throws CartNotFoundException {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getOrderDate(),
                orderDto.getTotalPrice(),
                cartRepo.findById(orderDto.getCartId()).orElseThrow(CartNotFoundException::new)
        );
    }

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getOrderId()
        );
    }
}
