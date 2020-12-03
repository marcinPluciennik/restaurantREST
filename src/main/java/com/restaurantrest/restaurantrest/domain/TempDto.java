package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TempDto {
    private Long tempId;
    private LocalDate date;
    private double temp;
}
