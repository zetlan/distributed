package net.zetlan.examples.api.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.zetlan.examples.api.BaseView;
import net.zetlan.examples.api.OrderItemView;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOrderView extends BaseView {
    private String userName;
    private List<OrderItemView> items;
    private Integer totalCostCents;
    private Integer totalItems;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderItemView> getItems() {
        return items;
    }

    public void setItems(List<OrderItemView> items) {
        this.items = items;
    }

    public Integer getTotalCostCents() {
        return totalCostCents;
    }

    public void setTotalCostCents(Integer totalCostCents) {
        this.totalCostCents = totalCostCents;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
