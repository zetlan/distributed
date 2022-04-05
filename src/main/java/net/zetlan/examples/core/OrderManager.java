package net.zetlan.examples.core;

import net.zetlan.examples.api.OrderRequests;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.db.Order;
import net.zetlan.examples.db.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderManager extends BaseManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManager.class);
    private static int ORDER_ID_SEQUENCE = 1;

    private static final Map<Integer, Order> ORDERS = new HashMap<>();

    @Inject
    public OrderManager() {
    }

    public Order placeOrder(Integer userId, OrderRequests.PlaceOrder request, List<ProductView> productViews) {
        List<OrderItem> orderItems = getOrderItems(productViews, request.getProductQuantities());

        Order order = constructOrder(userId, orderItems);
        return saveOrder(order);
    }

    public Order getByOrderId(Integer orderId) {
        return ORDERS.get(orderId);
    }

    private Order saveOrder(Order order) {
        ORDERS.put(ORDER_ID_SEQUENCE, order);
        ORDER_ID_SEQUENCE = ORDER_ID_SEQUENCE + 1;
        return order;
    }

    private List<OrderItem> getOrderItems(List<ProductView> productViews, Map<String, Integer> productQuantities) {
        Map<String, ProductView> skuToProduct = productViews.stream().collect(Collectors.toMap(ProductView::getSku, Function.identity()));

        List<OrderItem> orderItems = new ArrayList<>();
        for (var itemEntry : productQuantities.entrySet()) {
            String sku = itemEntry.getKey();
            Integer quantity = itemEntry.getValue();
            ProductView product = skuToProduct.get(sku);
            if (product == null) {
                LOGGER.error("SKU not found: [{}]", sku);
                throw new WebApplicationException("Product not found");
            }
            OrderItem orderItem = constructOrderItem(product.getId(), quantity, product.getPriceCents());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private OrderItem constructOrderItem(Integer productId, Integer quantity, Integer priceEachCents) {
        OrderItem item = new OrderItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPriceEachCents(priceEachCents);

        return item;
    }

    private Order constructOrder(Integer userId, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setId(ORDER_ID_SEQUENCE);
        ORDER_ID_SEQUENCE = ORDER_ID_SEQUENCE + 1;
        order.setOrderItems(orderItems);
        order.setUserId(userId);

        return order;
    }
}
