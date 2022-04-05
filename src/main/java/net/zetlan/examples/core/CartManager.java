package net.zetlan.examples.core;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.zetlan.examples.db.Cart;
import net.zetlan.examples.db.User;
import net.zetlan.examples.db.OrderItem;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class CartManager extends BaseManager {
    //store the list of carts
    private static final Map<Integer, Cart> CART_LIST = new HashMap<>();

    @Inject
    public CartManager() {

    }

    //creates a cart for a user
    private Cart createCartFor(Integer userID) {
        this.CART_LIST.put(userID, new Cart());
        Cart ct = this.CART_LIST.get(userID);
        ct.setUserId(userID);
        return ct;
    }

    // 1. Get a cart for a user
    public Cart getCartFor(Integer userID) {
        if (!this.hasCartFor(userID)) {
            this.createCartFor((userID));
        }
        return this.CART_LIST.get(userID);
    }

    public Boolean hasCartFor(Integer userID) {
        return this.CART_LIST.containsKey(userID);
    }

    // 2. Add an item to a cart for a user
    public Cart addItemTo(Integer userID, String sku, Integer quantity) {
        Cart cart = this.getCartFor(userID);
        cart.addToCart(sku, quantity);
        return cart;
    }

    // 3. Remove an item from a cart
    public Cart removeItemFrom(Integer userID, String sku, Integer quantity) {
        Cart cart = this.getCartFor(userID);
        cart.removeFromCart(sku, quantity);
        return cart;
    }

    // 4. Clear the cart
    public Cart clearCartFor(Integer userID) {
        Cart cart = this.getCartFor(userID);
        cart.clearCart();
        return cart;
    }
}

