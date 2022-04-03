package net.zetlan.examples.core;

import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.api.UserView;
import net.zetlan.examples.db.Product;
import net.zetlan.examples.db.User;

public class Mapper {
    private Mapper() { }

    public static UserView map(User user) {
        UserView view = new UserView();
        view.setId(user.getId());
        view.setName(user.getName());

        return view;
    }

    public static ProductView map(Product product) {
        ProductView view = new ProductView();

        view.setId(product.getId());
        view.setName(product.getName());
        view.setSku(product.getSku());
        view.setPriceCents(product.getPriceCents());

        return view;
    }
}
