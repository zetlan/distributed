package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemView extends BaseView {
    private String sku;
    private String name;
    private Integer priceEachCents = 0;
    private Integer quantity = 0;
    private Integer itemTotalCents = 0;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriceEachCents() {
        return priceEachCents;
    }

    public void setPriceEachCents(Integer priceEachCents) {
        this.priceEachCents = priceEachCents;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemTotalCents() {
        return itemTotalCents;
    }

    public void setItemTotalCents(Integer itemTotalCents) {
        this.itemTotalCents = itemTotalCents;
    }
}
