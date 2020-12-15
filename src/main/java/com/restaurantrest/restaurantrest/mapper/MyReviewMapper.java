package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.MyReview;
import com.restaurantrest.restaurantrest.domain.MyReviewDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyReviewMapper {

    public MyReview mapToReview(final MyReviewDto myReviewDto){
        return new MyReview(
                myReviewDto.getReviewId(),
                myReviewDto.getReviewText(),
                myReviewDto.getRating());
    }

    public MyReviewDto mapToReviewDto(final MyReview myReview){
        return new MyReviewDto(
                myReview.getReviewId(),
                myReview.getReviewText(),
                myReview.getRating());
    }

    public List<MyReviewDto> mapToReviewDtoList(final List<MyReview> myReviewList){
        return myReviewList.stream()
                .map(r -> new MyReviewDto(r.getReviewId(), r.getReviewText(), r.getRating()))
                .collect(Collectors.toList());
    }
}
