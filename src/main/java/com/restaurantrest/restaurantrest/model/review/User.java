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
    "name",
    "foodie_color",
    "profile_url",
    "profile_image",
    "profile_deeplink",
    "zomato_handle",
    "foodie_level",
    "foodie_level_num"
})
public class User {

    @JsonProperty("name")
    private String name;
    @JsonProperty("foodie_color")
    private String foodieColor;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("profile_deeplink")
    private String profileDeeplink;
    @JsonProperty("zomato_handle")
    private String zomatoHandle;
    @JsonProperty("foodie_level")
    private String foodieLevel;
    @JsonProperty("foodie_level_num")
    private Integer foodieLevelNum;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("foodie_color")
    public String getFoodieColor() {
        return foodieColor;
    }

    @JsonProperty("foodie_color")
    public void setFoodieColor(String foodieColor) {
        this.foodieColor = foodieColor;
    }

    @JsonProperty("profile_url")
    public String getProfileUrl() {
        return profileUrl;
    }

    @JsonProperty("profile_url")
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @JsonProperty("profile_image")
    public String getProfileImage() {
        return profileImage;
    }

    @JsonProperty("profile_image")
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @JsonProperty("profile_deeplink")
    public String getProfileDeeplink() {
        return profileDeeplink;
    }

    @JsonProperty("profile_deeplink")
    public void setProfileDeeplink(String profileDeeplink) {
        this.profileDeeplink = profileDeeplink;
    }

    @JsonProperty("zomato_handle")
    public String getZomatoHandle() {
        return zomatoHandle;
    }

    @JsonProperty("zomato_handle")
    public void setZomatoHandle(String zomatoHandle) {
        this.zomatoHandle = zomatoHandle;
    }

    @JsonProperty("foodie_level")
    public String getFoodieLevel() {
        return foodieLevel;
    }

    @JsonProperty("foodie_level")
    public void setFoodieLevel(String foodieLevel) {
        this.foodieLevel = foodieLevel;
    }

    @JsonProperty("foodie_level_num")
    public Integer getFoodieLevelNum() {
        return foodieLevelNum;
    }

    @JsonProperty("foodie_level_num")
    public void setFoodieLevelNum(Integer foodieLevelNum) {
        this.foodieLevelNum = foodieLevelNum;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
