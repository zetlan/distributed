package net.zetlan.examples.resources.api;

import com.google.common.base.Preconditions;
import net.zetlan.examples.Utils;
import net.zetlan.examples.api.CartView;
import net.zetlan.examples.api.OrderRequests;
import net.zetlan.examples.api.OrderView;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.api.gateway.UserOrderView;
import net.zetlan.examples.client.CartClient;
import net.zetlan.examples.client.OrderClient;
import net.zetlan.examples.client.UserClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiGatewayResource {

    private final CartClient cartClient;
    private final OrderClient orderClient;
    private final UserClient userClient;

    @Inject
    public ApiGatewayResource(
            final CartClient cartClient,
            final OrderClient orderClient,
            final UserClient userClient) {
        this.cartClient = Preconditions.checkNotNull(cartClient, "cartClient may not be null");
        this.orderClient = Preconditions.checkNotNull(orderClient, "orderClient may not be null");
        this.userClient = Preconditions.checkNotNull(userClient, "userClient may not be null");
    }

    /**
     * Orders whatever is in the user's cart at the moment.
     *
     * @param userName - name of the user
     * @return the placed {@link OrderView}
     */
    @POST
    @Path("/user/{user_name}/cart/order")
    public UserOrderView placeOrderForCart(@PathParam("user_name") final String userName) {
        UserView userView = userClient.getByName(userName);
        // get the cart
        CartView cartView = cartClient.getOrCreateForUser(userView.getId());
        Map<String, Integer> skuQuantities = Optional.ofNullable(cartView)
                .orElseThrow(WebApplicationException::new)
                .getSkuQuantities();
        if (Utils.isEmpty(skuQuantities)) {
            throw new WebApplicationException("Nothing to order", Response.serverError().build());
        }

        // Prep order requests for each item in the cart
        OrderRequests.PlaceOrder orderRequest = new OrderRequests.PlaceOrder();
        orderRequest.setUserId(userView.getId());
        orderRequest.setProductQuantities(skuQuantities);

        // use the order client to place the order
        OrderView orderView = orderClient.placeOrder(orderRequest);

        // return a consolidated OrderView
        return buildUserOrderView(orderView, userView);
    }

    @GET
    @Path("order/{id:[0-9]+}")
    public UserOrderView getOrder(@PathParam("id") final Integer orderId) {
        OrderView orderView = orderClient.getById(orderId);
        UserView userView = userClient.getById(orderView.getUserId());
        return buildUserOrderView(orderView, userView);
    }

    private UserOrderView buildUserOrderView(OrderView orderView, UserView userView) {
        UserOrderView view = new UserOrderView();
        view.setItems(orderView.getOrderItems());
        view.setTotalCostCents(orderView.getTotalCostCents());
        view.setTotalCostCents(orderView.getOrderItems().size());
        view.setUserName(userView.getName());

        return view;
    }

}
