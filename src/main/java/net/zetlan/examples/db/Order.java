package net.zetlan.examples.db;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity {
    private Integer userId;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> addItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        return this.orderItems;
    }
}
