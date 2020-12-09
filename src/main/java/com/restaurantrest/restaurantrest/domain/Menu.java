package com.restaurantrest.restaurantrest.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "MENUS")
public class Menu {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "DAILY_DATE")
    private LocalDateTime startDate;

    @OneToMany(targetEntity = Dish.class,
            mappedBy = "menu",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Dish> dishesList = new ArrayList<>();

    /*
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_DISH_ID")
    private Dish dish;

     */

    public Menu(String menuName, LocalDateTime startDate) {
        this.menuName = menuName;
        this.startDate = startDate;
    }
}
