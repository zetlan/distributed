package net.zetlan.examples.resources.api;

import com.google.common.base.Preconditions;
import net.zetlan.examples.api.OrderView;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.client.ProductClient;
import net.zetlan.examples.client.UserClient;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiGatewayResource {

    private final ProductClient productClient;
    private final UserClient userClient;

    @Inject
    public ApiGatewayResource(
            final ProductClient productClient,
            final UserClient userClient) {
        this.productClient = Preconditions.checkNotNull(productClient, "productClient may not be null");
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
    public OrderView placeOrderForCart(@PathParam("user_name") final String userName) {
        UserView userView = userClient.getByName(userName);
        // get the cart
        // get the product catalog
        List<ProductView> catalog = productClient.getProductCatalog();

        // Prep order requests for each item in the cart

        // use the order client to place the order

        // return a consolidated OrderView

        return null;
    }

}
