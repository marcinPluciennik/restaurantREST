package com.restaurantrest.restaurantrest.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurantrest.restaurantrest.model.DailyMenu_;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "daily_menu"
})
public class DailyMenu {

    @JsonProperty("daily_menu")
    private DailyMenu_ dailyMenu;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("daily_menu")
    public DailyMenu_ getDailyMenu() {
        return dailyMenu;
    }

    @JsonProperty("daily_menu")
    public void setDailyMenu(DailyMenu_ dailyMenu) {
        this.dailyMenu = dailyMenu;
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
