package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {
}
