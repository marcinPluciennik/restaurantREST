package com.restaurantrest.restaurantrest.conroller;

import com.google.gson.Gson;
import com.restaurantrest.restaurantrest.client.RestaurantClient;
import com.restaurantrest.restaurantrest.domain.Dish;
import com.restaurantrest.restaurantrest.domain.DishDto;
import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.domain.MyReviewDto;
import com.restaurantrest.restaurantrest.mapper.MyReviewMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyReviewController.class)
public class MyReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantClient restaurantClient;

    @MockBean
    private DbService service;

    @MockBean
    private MyReviewMapper myReviewMapper;

    @Test
    public void shouldFetchEmptyReviewList() throws Exception {
        //Given
        List<MyReviewDto> myReviewDtoList = new ArrayList<>();
        List<MyReview> myReviewList = new ArrayList<>();

        when(service.getReviews()).thenReturn(myReviewList);
        when(myReviewMapper.mapToReviewDtoList(myReviewList)).thenReturn(myReviewDtoList);

        //When & Then
        mockMvc.perform(get("/reviews/getMyReviews")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchReviewList() throws Exception {
        //Given
        List<MyReview> myReviewList = new ArrayList<>();
        MyReview myReview1 = new MyReview(1L, "test1", 5);
        MyReview myReview2 = new MyReview(2L, "test2", 4);
        MyReview myReview3 = new MyReview(3L, "test3", 3);
        myReviewList.add(myReview1);
        myReviewList.add(myReview2);
        myReviewList.add(myReview3);

        List<MyReviewDto> myReviewDtoList = new ArrayList<>();
        MyReviewDto myReviewDto1 = new MyReviewDto(1L, "test1", 5);
        MyReviewDto myReviewDto2 = new MyReviewDto(2L, "test2", 4);
        MyReviewDto myReviewDto3 = new MyReviewDto(3L, "test3", 3);
        myReviewDtoList.add(myReviewDto1);
        myReviewDtoList.add(myReviewDto2);
        myReviewDtoList.add(myReviewDto3);

        when(service.getReviews()).thenReturn(myReviewList);
        when(myReviewMapper.mapToReviewDtoList(myReviewList)).thenReturn(myReviewDtoList);

        //When & Then
        mockMvc.perform(get("/reviews/getMyReviews")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].reviewId", is(1)))
                .andExpect(jsonPath("$[1].reviewId", is(2)))
                .andExpect(jsonPath("$[2].reviewId", is(3)))
                .andExpect(jsonPath("$[0].reviewText", is("test1")))
                .andExpect(jsonPath("$[1].reviewText", is("test2")))
                .andExpect(jsonPath("$[2].reviewText", is("test3")))
                .andExpect(jsonPath("$[0].rating", is(5)))
                .andExpect(jsonPath("$[1].rating", is(4)))
                .andExpect(jsonPath("$[2].rating", is(3)));
    }

    @Test
    public void shouldFetchReviewById() throws Exception {
        //Given
        MyReviewDto myReviewDto = new MyReviewDto(1L, "test", 5);
        MyReview myReview = new MyReview(1L, "test", 5);
        Long reviewId = myReview.getReviewId();

        when(service.findMyReviewById(reviewId)).thenReturn(myReview);
        when(myReviewMapper.mapToReviewDto(myReview)).thenReturn(myReviewDto);

        //When & Then
        mockMvc.perform(get("/reviews/getMyReview/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviewId", is(1)))
                .andExpect(jsonPath("$.reviewText", is("test")))
                .andExpect(jsonPath("$.rating", is(5)));
    }

    @Test
    public void shouldDeleteReviewById() throws Exception{
        //Given
        MyReview myReview = new MyReview(1L, "test", 5);
        Long reviewId = myReview.getReviewId();
        List<MyReview> myReviewList = new ArrayList<>();
        myReviewList.add(myReview);

        when(service.removeMyReviewById(reviewId)).thenReturn(true);

        //When & Then
        mockMvc.perform(delete("/reviews/removeMyReview/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateReview() throws Exception{
        //Given
        MyReviewDto myReviewDto = new MyReviewDto(1L, "test", 5);

        doNothing().when(service).addOneMyReview(myReviewDto.getReviewText(), myReviewDto.getRating());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(myReviewDto);

        //When & Then
        mockMvc.perform(post("/reviews/addMyReview")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditReview() throws Exception{
        //Given
        MyReviewDto myReviewDto = new MyReviewDto(1L, "test", 5);
        MyReview myReview = new MyReview(1L, "test", 5);

        when(myReviewMapper.mapToReview(myReviewDto)).thenReturn(myReview);
        doNothing().when(service).updateMyReview(myReview);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(myReviewDto);

        //When & Then
        mockMvc.perform(put("/reviews/editMyReview")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}