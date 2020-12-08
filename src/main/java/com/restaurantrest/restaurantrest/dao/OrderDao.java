package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderDao extends JpaRepository<Order, Long> {

    Optional<List<Order>> findOrdersByOrderDate(LocalDate date);
}
