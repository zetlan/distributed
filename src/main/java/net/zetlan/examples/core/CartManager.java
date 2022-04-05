package net.zetlan.examples.core;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.zetlan.examples.db.Cart;
import net.zetlan.examples.db.User;
import net.zetlan.examples.db.OrderItem;
import net.zetlan.examples.api.CartRequests;

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
        Integer quantity = itemRequest.getQuantity();
        Cart cart = this.getCartFor(userID);

        //do the adding / removing
        if (quantity < 0) {
            cart.removeFromCart(sku, quantity);
        } else {
            cart.addToCart(sku, quantity);
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

