package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashMap;
import java.util.Map;

public class OrderRequests {

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlaceOrder {
        private String userName;
        private Map<String, Integer> productQuantities = new HashMap<>();

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Map<String, Integer> getProductQuantities() {
            return productQuantities;
        }

        public void setProductQuantities(Map<String, Integer> productQuantities) {
            this.productQuantities = productQuantities;
        }
    }
}
