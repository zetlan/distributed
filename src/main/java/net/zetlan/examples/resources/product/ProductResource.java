package net.zetlan.examples.resources.product;

import com.google.common.base.Preconditions;
import net.zetlan.examples.api.ProductView;
import net.zetlan.examples.core.Mapper;
import net.zetlan.examples.core.ProductManager;
import net.zetlan.examples.db.Product;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductManager productManager;

    @Inject
    public ProductResource(final ProductManager productManager) {
        this.productManager = Preconditions.checkNotNull(productManager, "productManager may not be null");
    }

    @GET
    public List<ProductView> getProductCatalog() {
        return productManager.getProductCatalog()
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList());
    }

    @GET
    @Path("sku/{sku}")
    public ProductView getBySku(@PathParam("sku") final String sku) {
        if (StringUtils.isEmpty(sku)) {
            throw new WebApplicationException("No SKU provided");
        }
        Product product = productManager.getBySku(sku);
        if (product == null) {
            throw new WebApplicationException(Response.noContent().build());
        }
        return Mapper.map(productManager.getBySku(sku));
    }
}
