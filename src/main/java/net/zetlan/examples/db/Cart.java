package net.zetlan.examples.db;

import java.util.ArrayList;
import java.util.List;

public class Cart extends BaseEntity {
    private User user;
    private List<Product> cartItems = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    public void addItem(Product newItem) {
        this.cartItems.add(newItem);
    }

    public void clearCart() {
        this.cartItems = new ArrayList<>();
    }
}
