package net.zetlan.examples.core;

import net.zetlan.examples.api.UserView;
import net.zetlan.examples.db.User;

public class Mapper {
    private Mapper() { }

    public static UserView map(User user) {
        UserView view = new UserView();
        view.setId(user.getId());
        view.setName(user.getName());

        return view;
    }
}
