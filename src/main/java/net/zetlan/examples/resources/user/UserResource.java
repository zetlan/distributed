package net.zetlan.examples.resources.user;

import com.google.common.base.Preconditions;
import io.dropwizard.hibernate.UnitOfWork;
import net.zetlan.examples.api.UserRequests;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.core.Mapper;
import net.zetlan.examples.core.UserManager;
import net.zetlan.examples.db.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserManager userManager;

    @Inject
    public UserResource(final UserManager userManager) {
        this.userManager = Preconditions.checkNotNull(userManager);
    }

    @GET
    @UnitOfWork
    public List<UserView> listUsers() {
        return userManager.getUsers()
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{user_name}")
    public UserView getByName(@PathParam("user_name") final String userName) {
        return Mapper.map(userManager.getByName(userName));
    }

    @GET
    @Path("/id/{id:[0-9]+}")
    public UserView getById(@PathParam("id") final Integer userId) {
        User user = userManager.getById(userId);
        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }
        return Mapper.map(user);
    }


    @POST
    @UnitOfWork
    public UserView createUser(UserRequests.CreateUser createUser) {
        return Mapper.map(userManager.createUser(createUser));
    }
}
