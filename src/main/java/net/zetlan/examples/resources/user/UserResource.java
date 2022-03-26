package net.zetlan.examples.resources.user;

import com.google.common.base.Preconditions;
import io.dropwizard.hibernate.UnitOfWork;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.core.Mapper;
import net.zetlan.examples.core.UserManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
