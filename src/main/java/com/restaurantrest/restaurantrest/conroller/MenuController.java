package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.mapper.MenuMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private DbService service;

    @Autowired
    private DishMapper dishMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createMenu", consumes = APPLICATION_JSON_VALUE)
    public MenuDto createMenu(@RequestBody MenuDto menuDto){
        return menuMapper.mapToMenuDto(service.saveMenu(menuMapper.mapToMenu(menuDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMenus")
    public List<MenuDto> getMenus(){
        List<Menu> menus = service.getMenus();
        return menuMapper.mapToMenuDtoList(menus);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMenu/{menuId}")
    public MenuDto getMenuById(@PathVariable Long menuId) throws MenuNotFoundException{
        return menuMapper.mapToMenuDto(service.findMenuById(menuId).orElseThrow(MenuNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeMenu/{menuId}")
    public void removeMenuById(@PathVariable Long menuId) {
        Optional<Menu> menuById = service.getMenus().stream()
                .filter(menu -> menu.getMenuId() == menuId)
                .findFirst();
        if (menuById.isPresent()) {
            service.removeMenuById(menuId);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editMenu")
    public void editMenuById(@RequestBody MenuDto menuDto){
        Optional<Menu> menuById = service.getMenus().stream()
                .filter(menu -> menu.getMenuId() == menuDto.getMenuId())
                .findFirst();
        if (menuById.isPresent()) {
            service.removeMenuById(menuDto.getMenuId());
            service.saveMenu(menuMapper.mapToMenu(menuDto));
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDishes/{menuId}")
    public List<DishDto> getDishesFromMenu(@PathVariable Long menuId){
        Optional<Menu> menuById = service.getMenus().stream()
                .filter(menu -> menu.getMenuId() == menuId)
                .findFirst();
        if (menuById.isPresent()) {
            Menu menu = menuById.get();
            List<Dish> dishesFromCart = menu.getDishesList();
            return dishMapper.mapToDishDtoList(dishesFromCart);
        }
        return new ArrayList<>();
    }
}
