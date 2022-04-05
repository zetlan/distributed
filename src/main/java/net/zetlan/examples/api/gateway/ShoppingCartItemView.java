package net.zetlan.examples.api.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.zetlan.examples.api.BaseView;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCartItemView extends BaseView {
    private String sku;
    private String name;
    private String quantity;
    private String priceEachCents;
    private String itemTotalCents;

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPriceEachCents() {
        return priceEachCents;
    }

    public void setPriceEachCents(String priceEachCents) {
        this.priceEachCents = priceEachCents;
    }

    public String getItemTotalCents() {
        return itemTotalCents;
    }

    public void setItemTotalCents(String itemTotalCents) {
        this.itemTotalCents = itemTotalCents;
    }
}
