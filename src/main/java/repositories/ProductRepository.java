package repositories;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.FeedResponse;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedIterable;
import models.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String DATABASE_ID = "MarketData";
    private static final String CONTAINER_ID = "Product";
    private final CosmosContainer container;

    private static ProductRepository instance;

    public ProductRepository(String endpoint, String key) {
        CosmosClient cosmosClient = new CosmosClientBuilder()
                .endpoint(endpoint)
                .key(key)
                .buildClient();

        container = cosmosClient.getDatabase(DATABASE_ID).getContainer(CONTAINER_ID);
    }

    public static synchronized ProductRepository getInstance(String endpoint, String key) {
        if (instance == null) {
            instance = new ProductRepository(endpoint, key);
        }
        return instance;
    }

    public List<Product> getAllItems() {
        List<Product> items = new ArrayList<Product>();

        String query = "SELECT * FROM c";
        SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(query);
        CosmosQueryRequestOptions requestOptions = new CosmosQueryRequestOptions();

        CosmosPagedIterable<Product> response = container.queryItems(sqlQuerySpec, requestOptions, Product.class);

        for (FeedResponse<Product> page : response.iterableByPage()) {
            items.addAll(page.getResults());
        }

        return items;
    }

    public Product getProduct(String id) {
        List<Product> items = new ArrayList<Product>();
        String query = "SELECT * FROM c WHERE c.id = \"" + id + "\"";
        SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(query);
        CosmosQueryRequestOptions requestOptions = new CosmosQueryRequestOptions();

        CosmosPagedIterable<Product> response = container.queryItems(sqlQuerySpec, requestOptions, Product.class);

        for (FeedResponse<Product> page : response.iterableByPage()) {
            items.addAll(page.getResults());
        }

        return items.size() > 0 ? items.get(0) : null;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> items = new ArrayList<Product>();

        String query = "SELECT * FROM c WHERE c.name = \"" + name + "\"";
        SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(query);
        CosmosQueryRequestOptions requestOptions = new CosmosQueryRequestOptions();

        CosmosPagedIterable<Product> response = container.queryItems(sqlQuerySpec, requestOptions, Product.class);

        for (FeedResponse<Product> page : response.iterableByPage()) {
            items.addAll(page.getResults());
        }

        return items;
    }

    public void addProduct(Product product) {
        container.createItem(product);
    }

    public void deleteProduct(String id) {
        Product existingProduct = getProduct(id);
        if (existingProduct != null) {
            container.deleteItem(existingProduct.getId(), new CosmosItemRequestOptions());
        }
    }
}
