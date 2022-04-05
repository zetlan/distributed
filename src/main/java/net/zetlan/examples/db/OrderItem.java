package net.zetlan.examples.db;

public class OrderItem extends BaseEntity {
    private Integer productId;
    private Integer quantity = 1;
    private Integer priceEachCents = 0;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPriceEachCents() {
        return priceEachCents;
    }

    public void setPriceEachCents(Integer priceEachCents) {
        this.priceEachCents = priceEachCents;
    }
}
