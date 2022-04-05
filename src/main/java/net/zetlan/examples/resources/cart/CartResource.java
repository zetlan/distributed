package net.zetlan.examples.resources.cart;

import com.google.common.base.Preconditions;
import net.zetlan.examples.api.CartRequests;
import net.zetlan.examples.api.CartView;
import net.zetlan.examples.core.CartManager;
import net.zetlan.examples.core.Mapper;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartManager cartManager;

    @Inject
    public CartResource(final CartManager cartManager) {
        this.cartManager = Preconditions.checkNotNull(cartManager, "cartManager may not be null");

    }

    @GET
    @Path("for-user/{user_id}")
    public CartView getOrCreateCart(@PathParam("user_id") final Integer userId) {
        return Mapper.map(cartManager.getCartFor(userId));
    }

    @PUT
    @Path("for-user/{user_id}")
    public CartView changeCart(@PathParam("user_id") final Integer userId, @Valid CartRequests.ChangeItems request) {
        return Mapper.map(cartManager.addOrRemoveItemFor(userId, request));
    }

    @DELETE
    @Path("for-user/{user_id}")
    public CartView clearCart(@PathParam("user_id") final Integer userId) {
        return Mapper.map(cartManager.clearCartFor(userId));
    }
}
