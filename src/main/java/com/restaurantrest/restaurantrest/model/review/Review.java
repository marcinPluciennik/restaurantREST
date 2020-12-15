package com.restaurantrest.restaurantrest.model.review;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rating",
    "review_text",
    "id",
    "rating_color",
    "review_time_friendly",
    "rating_text",
    "timestamp",
    "likes",
    "user",
    "comments_count"
})
public class Review {

    @JsonProperty("rating")
    private Integer rating;
    @JsonProperty("review_text")
    private String reviewText;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("rating_color")
    private String ratingColor;
    @JsonProperty("review_time_friendly")
    private String reviewTimeFriendly;
    @JsonProperty("rating_text")
    private String ratingText;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("user")
    private User user;
    @JsonProperty("comments_count")
    private Integer commentsCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("rating")
    public Integer getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @JsonProperty("review_text")
    public String getReviewText() {
        return reviewText;
    }

    @JsonProperty("review_text")
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("rating_color")
    public String getRatingColor() {
        return ratingColor;
    }

    @JsonProperty("rating_color")
    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    @JsonProperty("review_time_friendly")
    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    @JsonProperty("review_time_friendly")
    public void setReviewTimeFriendly(String reviewTimeFriendly) {
        this.reviewTimeFriendly = reviewTimeFriendly;
    }

    @JsonProperty("rating_text")
    public String getRatingText() {
        return ratingText;
    }

    @JsonProperty("rating_text")
    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("likes")
    public Integer getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("comments_count")
    public Integer getCommentsCount() {
        return commentsCount;
    }

    @JsonProperty("comments_count")
    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
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
        return "Review{" +
                "rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                ", id=" + id +
                ", ratingColor='" + ratingColor + '\'' +
                ", reviewTimeFriendly='" + reviewTimeFriendly + '\'' +
                ", ratingText='" + ratingText + '\'' +
                ", timestamp=" + timestamp +
                ", likes=" + likes +
                ", user=" + user +
                ", commentsCount=" + commentsCount +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
