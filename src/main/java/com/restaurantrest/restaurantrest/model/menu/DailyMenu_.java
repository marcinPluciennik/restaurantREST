package com.restaurantrest.restaurantrest.model.menu;

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
    "daily_menu_id",
    "start_date",
    "end_date",
    "name",
    "dishes"
})
public class DailyMenu_ {

    @JsonProperty("daily_menu_id")
    private String dailyMenuId;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("dishes")
    private List<Dishh> dishes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("daily_menu_id")
    public String getDailyMenuId() {
        return dailyMenuId;
    }

    @JsonProperty("daily_menu_id")
    public void setDailyMenuId(String dailyMenuId) {
        this.dailyMenuId = dailyMenuId;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("dishes")
    public List<Dishh> getDishes() {
        return dishes;
    }

    @JsonProperty("dishes")
    public void setDishes(List<Dishh> dishes) {
        this.dishes = dishes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public DailyMenu_(String dailyMenuId, String startDate, String endDate, String name, List<Dishh> dishes) {
        this.dailyMenuId = dailyMenuId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.dishes = dishes;
    }

    public DailyMenu_() {
    }

    @Override
    public String toString() {
        return "DailyMenu_{" +
                "dailyMenuId='" + dailyMenuId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
