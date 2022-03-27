package net.zetlan.examples.cli;

public enum ServiceName {
    // Main API Gateway service: it communicates with other services to synthesize more complex views
    // than any one service can create on its own
    API_GATEWAY,

    // Deals with users only
    USER,

    // Services for placing and listing orders and order items
    ORDER,

    // Provides the product catalog
    PRODUCT,

    // Creates a "shopping cart" where items to purchase later can be stored for a user
    CART,

    // For testing/debugging of various things
    TEST
}
