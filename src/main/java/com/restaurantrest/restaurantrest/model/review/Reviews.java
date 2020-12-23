package com.restaurantrest.restaurantrest.model.review;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reviews_count",
    "reviews_start",
    "reviews_shown",
    "user_reviews"
})
public class Reviews {

    @JsonProperty("reviews_count")
    private Integer reviewsCount;
    @JsonProperty("reviews_start")
    private Integer reviewsStart;
    @JsonProperty("reviews_shown")
    private Integer reviewsShown;
    @JsonProperty("user_reviews")
    private List<UserReview> userReviews = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reviews_count")
    public Integer getReviewsCount() {
        return reviewsCount;
    }

    @JsonProperty("reviews_count")
    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    @JsonProperty("reviews_start")
    public Integer getReviewsStart() {
        return reviewsStart;
    }

    @JsonProperty("reviews_start")
    public void setReviewsStart(Integer reviewsStart) {
        this.reviewsStart = reviewsStart;
    }

    @JsonProperty("reviews_shown")
    public Integer getReviewsShown() {
        return reviewsShown;
    }

    @JsonProperty("reviews_shown")
    public void setReviewsShown(Integer reviewsShown) {
        this.reviewsShown = reviewsShown;
    }

    @JsonProperty("user_reviews")
    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    @JsonProperty("user_reviews")
    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "reviewsCount=" + reviewsCount +
                ", reviewsStart=" + reviewsStart +
                ", reviewsShown=" + reviewsShown +
                ", userReviews=" + userReviews +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public Reviews(Integer reviewsCount, Integer reviewsStart, Integer reviewsShown, List<UserReview> userReviews) {
        this.reviewsCount = reviewsCount;
        this.reviewsStart = reviewsStart;
        this.reviewsShown = reviewsShown;
        this.userReviews = userReviews;
    }

    public Reviews() {
    }
}
