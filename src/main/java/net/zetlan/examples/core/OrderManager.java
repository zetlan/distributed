package net.zetlan.examples.core;

import com.google.common.base.Preconditions;
import net.zetlan.examples.client.UserClient;
import net.zetlan.examples.db.Order;
import net.zetlan.examples.db.OrderItem;
import net.zetlan.examples.db.User;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager extends BaseManager {
    private static int ORDER_ID_SEQUENCE = 1;

    private static final Map<Integer, Order> ORDERS = new HashMap<>();

    private final UserClient userClient;

    @Inject
    public OrderManager(final UserClient userClient) {
        this.userClient = Preconditions.checkNotNull(userClient, "userClient may not be null");
    }

    private Order constructOrder(List<OrderItem> orderItems, User customer) {
        Order order = new Order();
        order.setId(ORDER_ID_SEQUENCE);
        ORDER_ID_SEQUENCE = ORDER_ID_SEQUENCE + 1;
        order.setOrderItems(orderItems);
        order.setUser(customer);

        return order;
    }
}
