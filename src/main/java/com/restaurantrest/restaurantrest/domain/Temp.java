package com.restaurantrest.restaurantrest.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TEMP_PRAGUE")
public class Temp {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMP_ID")
    private Long tempId;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TEMP")
    private double temp;

    public Temp(LocalDate date, double temp) {
        this.date = date;
        this.temp = temp;
    }

    public Temp(Long tempId, double temp) {
        this.tempId = tempId;
        this.temp = temp;
    }
}
