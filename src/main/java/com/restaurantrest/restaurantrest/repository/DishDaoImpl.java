package com.restaurantrest.restaurantrest.repository;

import com.restaurantrest.restaurantrest.dao.DishDao;
import com.restaurantrest.restaurantrest.domain.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DishDaoImpl implements DishDao {

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(TempDaoImpl.class);

    @Autowired
    public DishDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dish findDishById(long id) {
        Optional<Dish> dish = findAll().stream()
                .filter(d -> d.getDishId() == id)
                .findFirst();
        if (dish.isPresent()) {
            String sql = "SELECT * FROM dishes WHERE dishes.dish_id = ?";
            return jdbcTemplate.queryForObject(sql, (resultSet, column) -> new Dish(
                    resultSet.getLong("dish_id"),
                    resultSet.getString("name"),
                    new BigDecimal(resultSet.getString("price"))), id);
        }
        return new Dish();
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * FROM dishes";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> dishes.add(new Dish(
                        Long.parseLong(String.valueOf(element.get("dish_id"))),
                        String.valueOf(element.get("name")),
                        new BigDecimal(String.valueOf(element.get("price"))))));
        return dishes;
    }

    @Override
    public void saveExistingDishes(List<Dish> dishes) {
        try{
            String sql = "INSERT INTO dishes VALUES(?, ?, ?)";
            for (int i = 0; i < dishes.size(); i++) {
                jdbcTemplate.update(sql, dishes.get(i).getDishId(),
                        dishes.get(i).getName(), dishes.get(i).getPrice());
            }
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void save(String name, BigDecimal price) {
        Dish dish = new Dish(name, price);
        String sql = "INSERT INTO dishes VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, dish.getDishId(), dish.getName(),
                dish.getPrice());
    }

    @Override
    public void updateDish(Dish newDish) {
        String sql = "UPDATE dishes SET dishes.name=?, dishes.price=? WHERE dishes.dish_id=?";
        jdbcTemplate.update(sql, newDish.getName(), newDish.getPrice(), newDish.getDishId());
    }

    @Override
    public boolean deleteDishById(long id) {
        Optional<Dish> dish = findAll().stream()
                .filter(d -> d.getDishId() == id)
                .findFirst();
        if (dish.isPresent()){
            String sql = "DELETE FROM dishes WHERE dishes.dish_id = ?";
            jdbcTemplate.update(sql, id);
            return true;
        }
        return false;
    }
}





