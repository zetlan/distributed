package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class CartRequests {
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChangeItems {
        private String sku;
        private Integer difference;

        public String getSku() {
            return this.sku;
        }

        public Integer getDifference() {
            return this.difference;
        }

        public ChangeItems(String sku, Integer difference) {
            this.sku = sku;
            this.difference = difference;
        }
    }
}
