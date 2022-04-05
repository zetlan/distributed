package net.zetlan.examples.api.gateway;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.zetlan.examples.api.BaseView;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCartView extends BaseView {
    private List<ShoppingCartItemView> cartItems = new ArrayList<>();
    private Integer totalCostCents;

    public List<ShoppingCartItemView> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCartItemView> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getTotalCostCents() {
        return totalCostCents;
    }

    public void setTotalCostCents(Integer totalCostCents) {
        this.totalCostCents = totalCostCents;
    }
}
