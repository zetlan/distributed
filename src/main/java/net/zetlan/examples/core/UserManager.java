package net.zetlan.examples.core;

import com.google.common.base.Preconditions;
import net.zetlan.examples.db.User;
import net.zetlan.examples.db.UserDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserManager {
    private final UserDao userDao;

    @Inject
    public UserManager(final UserDao userDao) {
        this.userDao = Preconditions.checkNotNull(userDao, "UserDao Cannot be null");
    }

    public List<User> getUsers() {
        return userDao.all();
    }
}
