package com.restaurantrest.restaurantrest.repository;

import com.restaurantrest.restaurantrest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
