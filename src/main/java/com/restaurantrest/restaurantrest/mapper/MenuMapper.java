package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.conroller.DishNotFoundException;
import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.domain.Menu;
import com.restaurantrest.restaurantrest.domain.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    @Autowired
    private DishDao dishDao;

    public Menu mapToMenu(final MenuDto menuDto) throws DishNotFoundException {
        return new Menu(
                menuDto.getMenuId(),
                menuDto.getMenuName(),
                menuDto.getStartDate(),
                dishDao.findById(menuDto.getDishId()).orElseThrow(DishNotFoundException::new)
        );
    }

    public MenuDto mapToMenuDto(final Menu menu){
        return new MenuDto(
                menu.getMenuId(),
                menu.getMenuName(),
                menu.getStartDate(),
                menu.getDish().getDishId()
        );
    }
}
