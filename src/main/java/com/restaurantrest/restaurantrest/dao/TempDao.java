package com.restaurantrest.restaurantrest.dao;

import com.restaurantrest.restaurantrest.domain.Temp;

import java.time.LocalDate;
import java.util.List;

public interface TempDao {
    void saveTemp(long id, LocalDate date, double temp);
    void updateTemp(Temp newTemp);
    List<Temp> findAll();
    boolean deleteTemp(long id);
    Temp findTempByDate(LocalDate date);
}
