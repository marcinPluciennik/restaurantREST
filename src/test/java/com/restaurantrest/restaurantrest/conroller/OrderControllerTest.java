package com.restaurantrest.restaurantrest.conroller;

import com.google.gson.Gson;
import com.restaurantrest.restaurantrest.builder.OrderBuilder;
import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.OrderMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private OrderMapper orderMapper;

    @Test
    public void shouldFetchEmptyOrderList() throws Exception{
        //Given
        List<OrderDto>  orderDtoList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        when(service.getOrders()).thenReturn(orderList);
        when(orderMapper.mapToOrderDtoList(orderList)).thenReturn(orderDtoList);

        //When & Then
        mockMvc.perform(get("/orders/getOrders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchOrderList() throws Exception{
        //Given
        List<OrderDto> orderDtoList = new ArrayList<>();
        OrderDto orderDto = new OrderDto(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), 1L, 1L);
        orderDtoList.add(orderDto);

        List<Order> orderList = new ArrayList<>();
        User user = new User();
        Cart cart = new Cart();
        Order order = new Order(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), user, cart);
        orderList.add(order);

        when(service.getOrders()).thenReturn(orderList);
        when(orderMapper.mapToOrderDtoList(orderList)).thenReturn(orderDtoList);

        //When & Then
        mockMvc.perform(get("/orders/getOrders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].orderId", is(1)))
                .andExpect(jsonPath("$[0].totalPrice", is(100)))
                .andExpect(jsonPath("$[0].orderDate", is("2020-12-21")))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].cartId", is(1)));
    }

    @Test
    public void shouldFetchOrderById() throws Exception{
        //Given
        OrderDto orderDto = new OrderDto(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), 1L, 1L);
        User user = new User();
        Cart cart = new Cart();
        Order order = new Order(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), user, cart);
        Optional<Order> optionalOrder = Optional.of(order);
        Long orderId = order.getOrderId();

        when(service.findOrderById(orderId)).thenReturn(optionalOrder);
        when(orderMapper.mapToOrderDto(order)).thenReturn(orderDto);

        //When & Then
        mockMvc.perform(get("/orders/getOrder/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is(1)))
                .andExpect(jsonPath("$.totalPrice", is(100)))
                .andExpect(jsonPath("$.orderDate", is("2020-12-21")))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.cartId", is(1)));
    }

    @Test
    public void shouldFetchOrdersByDate() throws Exception{
        //Given
        OrderDto orderDto = new OrderDto(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), 1L, 1L);
        User user = new User();
        Cart cart = new Cart();
        Order order = new Order(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), user, cart);
        LocalDate date = order.getOrderDate();
        List<Order> myOrderList = new ArrayList<>();
        myOrderList.add(order);
        Optional<List<Order>> orderList = Optional.of(myOrderList);
        List<OrderDto> myOrderDtoList = new ArrayList<>();
        myOrderDtoList.add(orderDto);

        when(service.getOrdersByDate(date)).thenReturn(orderList);
        when(orderMapper.mapToOrderDtoList(myOrderList)).thenReturn(myOrderDtoList);

        //When & Then
        mockMvc.perform(get("/orders/getOrders/2020-12-21")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId", is(1)))
                .andExpect(jsonPath("$[0].totalPrice", is(100)))
                .andExpect(jsonPath("$[0].orderDate", is("2020-12-21")))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].cartId", is(1)));
    }

    @Test
    public void shouldDeleteOrderById() throws Exception{
        //Given
        Order order = new Order(1L, LocalDate.of(2020, 12, 21),
                new BigDecimal("100"), new User(), new Cart());
        List<Order> myOrderList = new ArrayList<>();
        myOrderList.add(order);

        when(service.getOrders()).thenReturn(myOrderList);
        Long orderId = 1L;
        doNothing().when(service).removeOrderById(orderId);

        //When & Then
        mockMvc.perform(delete("/orders/removeOrder/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateOrder() throws Exception{
        //Given
        Order order = new OrderBuilder().setTotalPrice(new BigDecimal(100)).createOrder();
        OrderDto orderDto = new OrderDto(new BigDecimal("100"));

        when(orderMapper.mapToOrder(any(OrderDto.class))).thenReturn(order);
        when(service.saveOrder(order)).thenReturn(order);
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(orderDto);

        //When & Then
        mockMvc.perform(post("/orders/createOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.totalPrice", is(100)))
                .andExpect(status().isOk());
    }
}