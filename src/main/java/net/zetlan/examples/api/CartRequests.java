package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class CartRequests {
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChangeItems {
        private String sku;
        private Integer amount;

        public String getSku() {
            return this.sku;
        }

        public Integer getQuantity() {
            return this.amount;
        }

        public ChangeItems(String sku, Integer amount) {
            this.sku = sku;
            this.amount = amount;
        }
    }
}
