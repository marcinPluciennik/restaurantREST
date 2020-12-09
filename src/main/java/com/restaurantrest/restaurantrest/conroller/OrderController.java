package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.Order;
import com.restaurantrest.restaurantrest.domain.OrderDto;
import com.restaurantrest.restaurantrest.mapper.OrderMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException{
        return orderMapper.mapToOrderDto(service.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders(){
        List<Order> orders = service.getOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrders/{date}")
    public List<OrderDto> getOrdersByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")
                                                      LocalDate date ) throws OrderNotFoundException{
        List<Order> orders = service.getOrdersByDate(date).orElseThrow(OrderNotFoundException::new);
        return orderMapper.mapToOrderDtoList(orders);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder/{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId) throws OrderNotFoundException{
        return orderMapper.mapToOrderDto(service.findOrderbyId(orderId).orElseThrow(OrderNotFoundException::new));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "removeOrder/{orderId}")
    public void removeOrderById(@PathVariable Long orderId) {
        Optional<Order> orderById = service.getOrders().stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst();
        if (orderById.isPresent()) {
            service.removeOrderById(orderId);
        }
    }

}
