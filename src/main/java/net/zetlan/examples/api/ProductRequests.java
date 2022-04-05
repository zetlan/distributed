package net.zetlan.examples.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.Collection;

public class ProductRequests {

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GetProducts {
        Collection<String> skus = new ArrayList<>();

        public Collection<String> getSkus() {
            return skus;
        }

        public void setSkus(Collection<String> skus) {
            this.skus = skus;
        }
    }
}
