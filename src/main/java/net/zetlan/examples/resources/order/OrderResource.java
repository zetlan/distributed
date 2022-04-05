package net.zetlan.examples.resources.order;

import com.google.common.base.Preconditions;
import net.zetlan.examples.api.OrderRequests;
import net.zetlan.examples.api.OrderView;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.client.ProductClient;
import net.zetlan.examples.client.UserClient;
import net.zetlan.examples.core.Mapper;
import net.zetlan.examples.core.OrderManager;
import net.zetlan.examples.db.Order;
import net.zetlan.examples.db.OrderItem;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderManager orderManager;
    private final ProductClient productClient;
    private final UserClient userClient;

    @Inject
    public OrderResource(final OrderManager orderManager,
                         final ProductClient productClient,
                         final UserClient userClient) {
        this.orderManager = Preconditions.checkNotNull(orderManager, "orderManager may not be null");
        this.productClient = Preconditions.checkNotNull(productClient, "productClient may not be null");
        this.userClient = Preconditions.checkNotNull(userClient, "userClient may not be null");
    }

    @POST
    public OrderView placeOrder(@Valid OrderRequests.PlaceOrder request) {
        UserView userView = userClient.getByName(request.getUserName());
        if (userView == null) {
            throw new WebApplicationException("User not found");
        }
        List<ProductView> productViews = productClient.getBySkus(request.getProductQuantities().keySet());
        Map<Integer, ProductView> idToProductView = productViews.stream().collect(Collectors.toMap(
                ProductView::getId, Function.identity()));

        return Mapper.map(orderManager.placeOrder(userView.getId(), request, productViews), userView, idToProductView);
    }

    @GET
    @Path("{id:[0-9]+}")
    public OrderView getById(@PathParam("id") final Integer orderId) {
        Order order = orderManager.getByOrderId(orderId);
        if (order == null) {
            throw new WebApplicationException("Order not found", Response.Status.NOT_FOUND);
        }
        UserView userView = userClient.getById(order.getUserId());
        if (userView == null) {
            throw new WebApplicationException("User not found");
        }

        List<Integer> productIds = order.getOrderItems().stream().map(OrderItem::getProductId).collect(Collectors.toList());
        List<ProductView> productViews = productClient.getByIds(productIds);
        Map<Integer, ProductView> idToProductView = productViews.stream().collect(Collectors.toMap(
                ProductView::getId, Function.identity()));

        return Mapper.map(order, userView, idToProductView);
    }
}
