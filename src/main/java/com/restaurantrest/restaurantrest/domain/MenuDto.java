package com.restaurantrest.restaurantrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private Long menuId;
    private String menuName;
    private LocalDateTime startDate;
    private List<Long> dishesIds;
}
