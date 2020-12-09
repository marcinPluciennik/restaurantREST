package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MenuMapper {

    @Autowired
    private DishDao dishDao;

    public Menu mapToMenu(final MenuDto menuDto){
        return new Menu(
                menuDto.getMenuId(),
                menuDto.getMenuName(),
                menuDto.getStartDate(),
                mapToDishesList(menuDto.getDishesIds()));
    }

    public MenuDto mapToMenuDto(final Menu menu){
        return new MenuDto(
                menu.getMenuId(),
                menu.getMenuName(),
                menu.getStartDate(),
                mapToDishesIdsList(menu.getDishesList()));
    }
    public List<Dish> mapToDishesList(final List<Long> dishesIds) {
        return dishesIds.stream()
                .map(dishId -> dishDao.findById(dishId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Long> mapToDishesIdsList(final List<Dish> dishesList) {
        return dishesList.stream()
                .map(Dish::getDishId)
                .collect(Collectors.toList());
    }

    public List<MenuDto> mapToMenuDtoList(final List<Menu> menuList){
        return menuList.stream()
                .map(m -> new MenuDto(m.getMenuId(), m.getMenuName(), m.getStartDate(),
                        mapToDishesIdsList(m.getDishesList())))
                .collect(Collectors.toList());
    }
}
