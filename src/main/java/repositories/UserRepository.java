package repositories;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.FeedResponse;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedIterable;
import models.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String DATABASE_ID = "MarketData";
    private static final String CONTAINER_ID = "User";
    private final CosmosContainer container;

    private static UserRepository instance;

    public UserRepository(String endpoint, String key) {
        CosmosClient cosmosClient = new CosmosClientBuilder()
                .endpoint(endpoint)
                .key(key)
                .buildClient();

        container = cosmosClient.getDatabase(DATABASE_ID).getContainer(CONTAINER_ID);
    }

    public static synchronized UserRepository getInstance(String endpoint, String key) {
        if (instance == null) {
            instance = new UserRepository(endpoint, key);
        }
        return instance;
    }

    public List<User> getAllItems() {
        List<User> items = new ArrayList<>();

        String query = "SELECT * FROM c";
        SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(query);
        CosmosQueryRequestOptions requestOptions = new CosmosQueryRequestOptions();

        CosmosPagedIterable<User> response = container.queryItems(sqlQuerySpec, requestOptions, User.class);

        for (FeedResponse<User> page : response.iterableByPage()) {
            items.addAll(page.getResults());
        }

        return items;
    }

    public User getUser(String id) {
        List<User> items = new ArrayList<>();
        String query = "SELECT * FROM c WHERE c.id = \"" + id + "\"";
        SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(query);
        CosmosQueryRequestOptions requestOptions = new CosmosQueryRequestOptions();

        CosmosPagedIterable<User> response = container.queryItems(sqlQuerySpec, requestOptions, User.class);

        for (FeedResponse<User> page : response.iterableByPage()) {
            items.addAll(page.getResults());
        }

        return items.size() > 0 ? items.get(0) : null;
    }

    public List<User> getUsersByName(String name) {
        List<User> items = new ArrayList<>();

        String query = "SELECT * FROM c WHERE c.name = \"" + name + "\"";
        SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(query);
        CosmosQueryRequestOptions requestOptions = new CosmosQueryRequestOptions();

        CosmosPagedIterable<User> response = container.queryItems(sqlQuerySpec, requestOptions, User.class);

        for (FeedResponse<User> page : response.iterableByPage()) {
            items.addAll(page.getResults());
        }

        return items;
    }

    public void addUser(User user) {
        container.createItem(user);
    }

    public void deleteUser(String id) {
        User existingUser = getUser(id);
        if (existingUser != null) {
            container.deleteItem(existingUser.getId(), new CosmosItemRequestOptions());
        }
    }
}
