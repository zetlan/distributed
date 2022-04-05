package net.zetlan.examples.db;

import javax.ws.rs.WebApplicationException;
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

    public void removeFromCart(String sku, Integer quantity) throws WebApplicationException {
        //can't remove an item that doesn't exist
        if (!this.skuQuantities.containsKey(sku)) {
            throw new WebApplicationException();
        }

        //can't remove more items than what's in the cart
        if (this.skuQuantities.get(sku) < quantity) {
            throw new WebApplicationException();
        }

        //now that the issues are out of the way, remove the item(s) from the cart
        this.skuQuantities.put(sku, this.skuQuantities.get(sku) - quantity);
    }
}
