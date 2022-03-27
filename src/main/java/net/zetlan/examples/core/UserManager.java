package net.zetlan.examples.core;

import net.zetlan.examples.Utils;
import net.zetlan.examples.api.UserRequests;
import net.zetlan.examples.db.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class UserManager {
    private static final Map<Integer, User> USER_LIST = new HashMap<>();
    private static int ID_SEQUENCE = 0;

    @Inject
    public UserManager() {
    }

    public Collection<User> getUsers() {
        return USER_LIST.values();
    }

    public User createUser(UserRequests.CreateUser createUser) {
        requireUniqueUserName(createUser.getName());
        User user = new User();
        user.setName(createUser.getName());
        ID_SEQUENCE = ID_SEQUENCE + 1;
        user.setId(ID_SEQUENCE);
        USER_LIST.put(user.getId(), user);
        return user;
    }

    private void requireUniqueUserName(String username) {
        List<User> existingUsers = USER_LIST.values()
                .stream()
                .filter(user -> username.equalsIgnoreCase(user.getName()))
                .collect(Collectors.toList());
        if (!Utils.isEmpty(existingUsers)) {
            throw new WebApplicationException("User names must be unique");
        }
    }
}
