package com.restaurantrest.restaurantrest.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "dishList")
    private List<Cart> cartList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FK_MENU_ID")
    private Menu menu;

    public Dish(Long dishId, String name, BigDecimal price) {
        this.dishId = dishId;
        this.name = name;
        this.price = price;
    }

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cartList=" + cartList +
                ", menu=" + menu +
                '}';
    }
}
