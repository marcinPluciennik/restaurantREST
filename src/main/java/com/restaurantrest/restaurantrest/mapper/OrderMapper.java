package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.CartNotFoundException;
import com.restaurantrest.restaurantrest.conroller.UserNotFoundException;
import com.restaurantrest.restaurantrest.dao.UserDao;
import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.OrderDto;
import com.restaurantrest.restaurantrest.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    public Order mapToOrder(final OrderDto orderDto) throws CartNotFoundException, UserNotFoundException {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getOrderDate(),
                orderDto.getTotalPrice(),
                userDao.findById(orderDto.getUserId()).orElseThrow(UserNotFoundException::new),
                cartDao.findById(orderDto.getCartId()).orElseThrow(CartNotFoundException::new));
    }

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getUser().getUserId(),
                order.getOrderId());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList){
        return orderList.stream()
                .map(o -> new OrderDto(o.getOrderId(), o.getOrderDate(), o.getTotalPrice(),
                        o.getUser().getUserId(), o.getCart().getCartId()))
                .collect(Collectors.toList());
    }

}
