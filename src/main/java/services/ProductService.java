package services;

import com.azure.cosmos.models.CosmosItemRequestOptions;
import repositories.ProductRepository;
import models.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;
    private static ProductService instance;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static synchronized ProductService getInstance(ProductRepository productRepository) {
        if (instance == null) {
            instance = new ProductService(productRepository);
        }
        return instance;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.getAllItems();
    }

    public Product getProductById(String id) {
        return this.productRepository.getProduct(id);
    }

    public List<Product> getProductsByName(String name) {
        return this.productRepository.getProductsByName(name);
    }

    public void addProduct(Product product) {
        this.productRepository.addProduct(product);
    }

    public void updateProduct(String id, Product updatedProduct) {
        Product existingProduct = this.productRepository.getProduct(id);
        if (existingProduct != null) {
            updatedProduct.setId(existingProduct.getId());
            this.productRepository.deleteProduct(id);
            this.productRepository.addProduct(updatedProduct);
        }
    }

    public void deleteProduct(String id) {
        this.productRepository.deleteProduct(id);
    }
}
