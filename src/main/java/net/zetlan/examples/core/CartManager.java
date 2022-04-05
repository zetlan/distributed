package net.zetlan.examples.core;

import net.zetlan.examples.api.CartRequests;
import net.zetlan.examples.db.Cart;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class CartManager extends BaseManager {
    //store the list of carts
    private final Map<Integer, Cart> CART_LIST = new HashMap<>();

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

    public boolean hasCartFor(Integer userID) {
        return this.CART_LIST.containsKey(userID);
    }

    // 2. Add an item to a cart for a user
    public Cart addOrRemoveItemFor(Integer userID, CartRequests.ChangeItems itemRequest) {
        //extract necessary references
        String sku = itemRequest.getSku();
        Integer difference = itemRequest.getDifference();
        Cart cart = this.getCartFor(userID);

        // do the adding / removing
        if (difference < 0) {
            cart.removeFromCart(sku, difference);
        } else {
            cart.addToCart(sku, difference);
        }
        return cart;
    }

    // 4. Clear the cart
    public Cart clearCartFor(Integer userID) {
        Cart cart = this.getCartFor(userID);
        cart.clearCart();
        return cart;
    }
}

