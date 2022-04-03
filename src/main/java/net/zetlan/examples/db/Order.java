package net.zetlan.examples.db;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity {
    private User user;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Integer totalCost = 0;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        totalCost = orderItems.stream()
                .map(orderItem -> orderItem.getQuantity() * orderItem.getProduct().getPriceCents())
                .reduce(0, Integer::sum);
    }

    public List<OrderItem> addItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        this.totalCost = this.totalCost + (orderItem.getQuantity() * orderItem.getProduct().getPriceCents());
        return this.orderItems;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

}
