package net.zetlan.examples.db;

import java.util.HashMap;
import java.util.Map;

public class Cart extends BaseEntity {
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

    public void clearCart() {
        this.skuQuantities.clear();
    }

    public void addToCart(String sku, Integer quantity) {
        Integer currentQuantity = this.skuQuantities.getOrDefault(sku, 0);
        currentQuantity = currentQuantity + quantity;
        this.skuQuantities.put(sku, currentQuantity);
    }
}
