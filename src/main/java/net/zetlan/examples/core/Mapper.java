package net.zetlan.examples.core;

import net.zetlan.examples.api.CartView;
import net.zetlan.examples.api.OrderItemView;
import net.zetlan.examples.api.OrderView;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.db.Cart;
import net.zetlan.examples.db.Order;
import net.zetlan.examples.db.OrderItem;
import net.zetlan.examples.db.Product;
import net.zetlan.examples.db.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mapper {
    private Mapper() { }

    public static UserView map(User user) {
        UserView view = new UserView();
        view.setId(user.getId());
        view.setName(user.getName());

        return view;
    }

    public static ProductView map(Product product) {
        ProductView view = new ProductView();

        view.setId(product.getId());
        view.setName(product.getName());
        view.setSku(product.getSku());
        view.setPriceCents(product.getPriceCents());

        return view;
    }

    public static CartView map(Cart cart) {
        CartView view = new CartView();
        view.setUserId(cart.getUserId());
        view.setSkuQuantities(cart.getSkuQuantities());
        return view;
    }

    public static OrderItemView map(OrderItem item, ProductView productView) {
        OrderItemView orderItemView = new OrderItemView();
        orderItemView.setSku(productView.getSku());
        orderItemView.setName(productView.getName());

        var itemCost = item.getQuantity() * item.getPriceEachCents();
        orderItemView.setPriceEachCents(item.getPriceEachCents());
        orderItemView.setItemTotalCents(itemCost);
        orderItemView.setQuantity(item.getQuantity());

        return orderItemView;
    }

    public static OrderView map(Order order, Map<Integer, ProductView> idToProductView) {
        OrderView view = new OrderView();
        view.setUserId(order.getUserId());
        int totalCost = 0;
        List<OrderItemView> orderItemViews = new ArrayList<>();
        for (var item : order.getOrderItems()) {
            ProductView productView = idToProductView.get(item.getProductId());
            OrderItemView orderItemView = map(item, productView);

            totalCost = totalCost + orderItemView.getItemTotalCents();

            orderItemViews.add(orderItemView);
        }
        view.setOrderItems(orderItemViews);
        view.setTotalCostCents(totalCost);
        return view;
    }
}
