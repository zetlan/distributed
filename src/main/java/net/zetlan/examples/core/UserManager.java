package net.zetlan.examples.core;

import net.zetlan.examples.api.UserRequests;
import net.zetlan.examples.db.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserManager extends BaseManager {
    private static final Map<Integer, User> USER_LIST = new HashMap<>();
    private static int ID_SEQUENCE = 0;

    @Inject
    public UserManager() {
    }

    public Collection<User> getUsers() {
        return USER_LIST.values();
    }

    public User createUser(UserRequests.CreateUser createUser) {
        requireUnique(USER_LIST, createUser.getName(), User::getName);
        User user = new User();
        user.setName(createUser.getName());
        ID_SEQUENCE = ID_SEQUENCE + 1;
        user.setId(ID_SEQUENCE);
        USER_LIST.put(user.getId(), user);
        return user;
    }
}
