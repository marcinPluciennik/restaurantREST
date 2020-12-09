package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.GET, value = "getDishes")
    public List<DishDto> getDishes(){
        List<Dish> dishes = service.getDishes();
        return dishMapper.mapToDishDtoList(dishes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDish/{dishId}")
    public DishDto getDishById(@PathVariable Long dishId) throws DishNotFoundException{
        return dishMapper.mapToDishDto(service.findDishById(dishId).orElseThrow(DishNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeDish/{dishId}")
    public void removeDishById(@PathVariable Long dishId) {
        Optional<Dish> dishById = service.getDishes().stream()
                .filter(dish -> dish.getDishId() == dishId)
                .findFirst();
        if (dishById.isPresent()) {
            service.removeDishById(dishId);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "createNewDish", consumes = APPLICATION_JSON_VALUE)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return dishMapper.mapToDishDto(service.saveDish(dishMapper.mapToDish(dishDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editDish")
    public void editDishById(@RequestBody DishDto dishDto){
        Optional<Dish> dishById = service.getDishes().stream()
                .filter(dish -> dish.getDishId() == dishDto.getDishId())
                .findFirst();
        if (dishById.isPresent()) {
            service.removeDishById(dishDto.getDishId());
            service.saveDish(dishMapper.mapToDish(dishDto));
        }
    }

}
