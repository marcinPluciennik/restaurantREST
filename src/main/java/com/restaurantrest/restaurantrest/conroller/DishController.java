package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.client.RestaurantClient;
import com.restaurantrest.restaurantrest.domain.*;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DbService service;

    @Autowired
    private RestaurantClient restaurantClient;

    @Scheduled(cron = "0 0 9 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "saveDishes", consumes = APPLICATION_JSON_VALUE)
    public void saveExistingDishes(){
        service.saveExistingDishes(restaurantClient.getDishList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDishes")
    public List<DishDto> getDishes(){
        return dishMapper.mapToDishDtoList(service.getDishes());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getDish/{dishId}")
    public DishDto getDishById(@PathVariable Long dishId){
        return dishMapper.mapToDishDto(service.findDishById(dishId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addDish", consumes = APPLICATION_JSON_VALUE)
    public void addDish(@RequestBody DishDto dishDto){
        service.addOneDish(dishDto.getName(), dishDto.getPrice());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editDish")
    public void editDishById(@RequestBody DishDto dishDto){
        service.updateDish(dishMapper.mapToDish(dishDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeDish/{dishId}")
    public boolean removeDishById(@PathVariable Long dishId) {
        return service.removeDishById(dishId);
    }
}
