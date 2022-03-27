package net.zetlan.examples.resources.test;

import com.google.common.base.Preconditions;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.client.UserClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
    private final UserClient userClient;
    @Inject
    public TestResource(final UserClient userClient) {
        this.userClient = Preconditions.checkNotNull(userClient, "User client cannot be null");

    }

    @Path("/users")
    @GET
    public List<UserView> getUsersFromUserService() {
        return userClient.getUsers();
    }
}
