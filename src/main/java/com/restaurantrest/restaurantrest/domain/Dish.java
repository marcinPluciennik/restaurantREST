package com.restaurantrest.restaurantrest.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DISHES")
public class Dish {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISH_ID")
    private Long dishId;

    @Column(name = "NAME")
    private String name;

    @Column(name= "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "FK_CART_ID")
    private Cart cart;


    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
