package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashMap;
import java.util.Map;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartView extends BaseView {
    private Integer userId;
    private Map<String, Integer> skuQuantities = new HashMap<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getSkuQuantities() {
        return skuQuantities;
    }

    public void setSkuQuantities(Map<String, Integer> skuQuantities) {
        this.skuQuantities = skuQuantities;
    }
}
