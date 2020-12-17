package com.restaurantrest.restaurantrest.client;

import com.restaurantrest.restaurantrest.config.RestaurantConfig;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.mapper.DishMapper;
import com.restaurantrest.restaurantrest.model.menu.DailyMenu;
import com.restaurantrest.restaurantrest.model.menu.DailyMenu_;
import com.restaurantrest.restaurantrest.model.menu.Dishh;
import com.restaurantrest.restaurantrest.model.menu.MainMenu;
import com.restaurantrest.restaurantrest.model.review.Reviews;
import com.restaurantrest.restaurantrest.model.review.UserReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RestaurantClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantClient.class);

    private RestTemplate restTemplate;

    private DishMapper dishMapper;

    private RestaurantConfig restaurantConfig;

    @Autowired
    public RestaurantClient(DishMapper dishMapper, RestaurantConfig restaurantConfig) {
        this.dishMapper = dishMapper;
        this.restaurantConfig = restaurantConfig;
        this.restTemplate = new RestTemplate();
        getDishList();
    }

    public List<UserReview> getUserReviews(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("user-key", restaurantConfig.getZomatoAppUserKey());
        HttpEntity httpEntity = new HttpEntity(headers);

        try{
            ResponseEntity<Reviews> reviews = restTemplate.exchange(
                    "https://developers.zomato.com/api/v2.1/reviews?res_id=16507624",
                    HttpMethod.GET,
                    httpEntity,
                    Reviews.class);
            return reviews.getBody().getUserReviews();
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<MyReview> getReviews(){
        List<MyReview> myReviews = new ArrayList<>();
        getUserReviews().stream()
                .map(r -> myReviews.add(new MyReview(r.getReview().getRatingText(), r.getReview().getRating())))
                .collect(Collectors.toList());
        return myReviews;
    }

    public List<DailyMenu> getDailyMenus(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("user-key", restaurantConfig.getZomatoAppUserKey());
        HttpEntity httpEntity = new HttpEntity(headers);

        try{
            ResponseEntity<MainMenu> mainMenu = restTemplate.exchange(
                    "https://developers.zomato.com/api/v2.1/dailymenu?res_id=16507624",
                    HttpMethod.GET,
                    httpEntity,
                    MainMenu.class);
            return mainMenu.getBody().getDailyMenus();
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<DailyMenu_> getDailyMenus_(){
        List<DailyMenu_> dailyMenu_s = new ArrayList<>();
        getDailyMenus().stream()
                .map(m -> dailyMenu_s.add(new DailyMenu_(
                        m.getDailyMenu().getDailyMenuId(),
                        m.getDailyMenu().getStartDate(),
                        m.getDailyMenu().getEndDate(),
                        m.getDailyMenu().getName(),
                        m.getDailyMenu().getDishes())))
                .collect(Collectors.toList());
        return dailyMenu_s;
    }

    public List<List<Dishh>> getDishhListList(){
        List<List<Dishh>> dishhListList = new ArrayList<>();
        getDailyMenus_().stream()
                .map(m -> dishhListList.add(new ArrayList<Dishh>(
                        m.getDishes())))
                .collect(Collectors.toList());
        return dishhListList;
    }

    public List<Dish> getDishList(){
        List<Dish> dishList = new ArrayList<>();
        List<List<Dishh>> dishhListList = getDishhListList();
        for (int i = 0; i < dishhListList.size(); i++){
            for(int j = 0; j < dishhListList.get(i).size();j++){
                dishList.add(new Dish(
                        dishhListList.get(i).get(j).getDish().getName(),
                        new BigDecimal(dishhListList.get(i).get(j).getDish().getPrice().substring(0, dishhListList.get(i).get(j).getDish().getPrice().length() - 3))));
            }
        }
        Stream.of(dishList).forEach(System.out::println);
        return dishList;
    }
}
