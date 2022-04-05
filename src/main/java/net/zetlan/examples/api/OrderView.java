package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderView extends BaseView {
    private Integer userId;
    private List<OrderItemView> orderItems = new ArrayList<>();
    private int totalCostCents = 0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderItemView> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemView> orderItems) {
        this.orderItems = orderItems;
    }

    public int getTotalCostCents() {
        return totalCostCents;
    }

    public void setTotalCostCents(int totalCostCents) {
        this.totalCostCents = totalCostCents;
    }
}
