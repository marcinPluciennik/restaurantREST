package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Temp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TempDao extends JpaRepository<Temp, Long> {
}
