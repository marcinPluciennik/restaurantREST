package com.restaurantrest.restaurantrest.client;

import com.restaurantrest.restaurantrest.config.RestaurantConfig;
import com.restaurantrest.restaurantrest.model.menu.*;
import com.restaurantrest.restaurantrest.model.review.Review;
import com.restaurantrest.restaurantrest.model.review.Reviews;
import com.restaurantrest.restaurantrest.model.review.User;
import com.restaurantrest.restaurantrest.model.review.UserReview;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RestaurantClientTest {

    @InjectMocks
    private RestaurantClient restaurantClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestaurantConfig restaurantConfig;

    @Test
    public void shouldFetchUserReviews(){
        //Given
        when(restaurantConfig.getZomatoAppUserKey()).thenReturn("test");

        User user = new User("name", "foodieColor", "profileUrl", "profileImage", "profileDeeplink",
                "zomatoHandle", "foodieLevel", 10);
        Review review = new Review(5, "reviewText", 1L, "ratingColor",
                "reviewTimeFriendly", "ratingText", 10, 20,
                user, 10);
        UserReview userReview = new UserReview(review);
        List<UserReview> userReviews = new ArrayList<>();
        userReviews.add(userReview);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("user-key", restaurantConfig.getZomatoAppUserKey());
        HttpEntity httpEntity = new HttpEntity(headers);

        Reviews reviewsStart = new Reviews(10,10,10, userReviews);
        ResponseEntity<Reviews> reviews = new ResponseEntity<Reviews>(reviewsStart, HttpStatus.OK);

        when(restTemplate.exchange(
                "https://developers.zomato.com/api/v2.1/reviews?res_id=16507624",
                HttpMethod.GET,
                httpEntity,
                Reviews.class)).thenReturn(reviews);
        //When
        List<UserReview> fetchedUserReviews = reviews.getBody().getUserReviews();

        //Then
        assertEquals(1, fetchedUserReviews.size());
        assertEquals(5, fetchedUserReviews.get(0).getReview().getRating());
        assertEquals("reviewText", fetchedUserReviews.get(0).getReview().getReviewText());
        assertEquals(1L, fetchedUserReviews.get(0).getReview().getId());
        assertEquals("ratingColor", fetchedUserReviews.get(0).getReview().getRatingColor());
        assertEquals("reviewTimeFriendly", fetchedUserReviews.get(0).getReview().getReviewTimeFriendly());
        assertEquals("ratingText", fetchedUserReviews.get(0).getReview().getRatingText());
        assertEquals(10, fetchedUserReviews.get(0).getReview().getTimestamp());
        assertEquals(20, fetchedUserReviews.get(0).getReview().getLikes());
        assertEquals(user, fetchedUserReviews.get(0).getReview().getUser());
        assertEquals(10, fetchedUserReviews.get(0).getReview().getCommentsCount());
        assertEquals("name", fetchedUserReviews.get(0).getReview().getUser().getName());
        assertEquals("foodieColor", fetchedUserReviews.get(0).getReview().getUser().getFoodieColor());
        assertEquals("profileUrl", fetchedUserReviews.get(0).getReview().getUser().getProfileUrl());
        assertEquals("profileImage", fetchedUserReviews.get(0).getReview().getUser().getProfileImage());
        assertEquals("zomatoHandle", fetchedUserReviews.get(0).getReview().getUser().getZomatoHandle());
        assertEquals("foodieLevel", fetchedUserReviews.get(0).getReview().getUser().getFoodieLevel());
        assertEquals(10, fetchedUserReviews.get(0).getReview().getUser().getFoodieLevelNum());
    }

    @Test
    public void shouldFetchDailyMenus(){
        //Given
        when(restaurantConfig.getZomatoAppUserKey()).thenReturn("test");

        DailyMenu_ dailyMenu_ = new DailyMenu_("dailyMenuId", "startDate",
                "endDate", "name", new ArrayList<>());
        DailyMenu dailyMenu = new DailyMenu(dailyMenu_);
        List<DailyMenu> dailyMenus = new ArrayList<>();
        dailyMenus.add(dailyMenu);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("user-key", restaurantConfig.getZomatoAppUserKey());
        HttpEntity httpEntity = new HttpEntity(headers);

        MainMenu mainMenuStart = new MainMenu(dailyMenus, "status");
        ResponseEntity<MainMenu> mainMenu = new ResponseEntity<MainMenu>(mainMenuStart, HttpStatus.OK);

        when(restTemplate.exchange(
                "https://developers.zomato.com/api/v2.1/dailymenu?res_id=16507624",
                HttpMethod.GET,
                httpEntity,
                MainMenu.class)).thenReturn(mainMenu);

        //When
        List<DailyMenu> fetchedDailyMenus = mainMenu.getBody().getDailyMenus();

        //Then
        assertEquals(1, fetchedDailyMenus.size());
        assertEquals("dailyMenuId", fetchedDailyMenus.get(0).getDailyMenu().getDailyMenuId());
        assertEquals("startDate", fetchedDailyMenus.get(0).getDailyMenu().getStartDate());
        assertEquals("endDate", fetchedDailyMenus.get(0).getDailyMenu().getEndDate());
        assertEquals("name", fetchedDailyMenus.get(0).getDailyMenu().getName());
        assertEquals(new ArrayList<>(), fetchedDailyMenus.get(0).getDailyMenu().getDishes());
    }

    @Test
    public void shouldFetchDishhList(){
        //Given
        Dish_ dish_ = new Dish_("dishId", "name", "price");
        Dishh dishh = new Dishh(dish_);
        List<Dishh> dishhList = new ArrayList<>();
        dishhList.add(dishh);
        List<List<Dishh>> dishhListList = new ArrayList<>();
        dishhListList.add(dishhList);
        //When //Then
        assertEquals(1, dishhListList.size());
        assertEquals("dishId", dishhListList.get(0).get(0).getDish().getDishId());
        assertEquals("price", dishhListList.get(0).get(0).getDish().getPrice());
        assertEquals("name", dishhListList.get(0).get(0).getDish().getName());
    }
}