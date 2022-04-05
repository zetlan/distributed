package net.zetlan.examples.core;

import net.zetlan.examples.db.Product;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductManager extends BaseManager {
    private static final Map<Integer, Product> PRODUCT_CATALOG = new HashMap<>();
    private static int PRODUCT_ID_SEQUENCE = 1;

    @Inject
    public ProductManager() {
        resetProductCatalog();
    }

    private void resetProductCatalog() {
        PRODUCT_CATALOG.clear();

        Product widget = createProduct("wid-1234", 1_00, "Widget");
        Product whatsit = createProduct("wat-1234", 2_00, "Whatsit");
        Product whatchamacallit = createProduct("wcit-456", 3_50, "Whatchamacallit");

        List.of(widget, whatsit, whatchamacallit)
                .forEach(product -> PRODUCT_CATALOG.put(product.getId(), product));
    }

    private Product createProduct(String sku, Integer priceCents, String name) {
        requireUnique(PRODUCT_CATALOG, sku, Product::getSku);
        var product = new Product();
        product.setId(PRODUCT_ID_SEQUENCE);
        product.setSku(sku);
        product.setPriceCents(priceCents);
        product.setName(name);
        PRODUCT_ID_SEQUENCE = PRODUCT_ID_SEQUENCE + 1;
        return product;
    }

    public Collection<Product> getProductCatalog() {
        return PRODUCT_CATALOG.values();
    }

    public Product getBySku(String sku) {
        return PRODUCT_CATALOG.values()
                .stream()
                .filter(product -> product.getSku().equals(sku))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getBySkus(Collection<String> skus) {
        return PRODUCT_CATALOG.values()
                .stream()
                .filter(product -> skus.contains(product.getSku()))
                .collect(Collectors.toList());
    }

    public List<Product> getByIds(List<Integer> productIds) {
        return productIds.stream().map(PRODUCT_CATALOG::get).collect(Collectors.toList());
    }

}
