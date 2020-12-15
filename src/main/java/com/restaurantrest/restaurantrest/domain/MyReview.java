package com.restaurantrest.restaurantrest.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "REVIEWS")
public class MyReview {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long reviewId;

    @Column(name ="REVIEW_TEXT")
    private String reviewText;

    @Column(name = "RATING")
    private int rating;

    public MyReview(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MyReview{" +
                "reviewId=" + reviewId +
                ", reviewText='" + reviewText + '\'' +
                ", rating=" + rating +
                '}';
    }
}
