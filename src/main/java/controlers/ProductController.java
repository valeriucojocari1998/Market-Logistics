package controlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.ClothingSize;
import enums.ProductStatus;
import helpers.EnumHelpers;
import models.product.Clothing;
import models.product.Food;
import models.product.Product;
import services.ProductService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;
    private static ProductController instance;

    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    public static synchronized ProductController getInstance(ProductService productService) {
        if (instance == null) {
            instance = new ProductController(productService);
        }

        return instance;
    }

    public void handleConsoleInputProduct(Scanner scanner) throws IOException {
        System.out.println("Please input the action you would like to perform:\n" +
                "1. Get all products\n" +
                "2. Get product by Id\n" +
                "3. Get products by Name\n" +
                "4. Add a product\n" +
                "5. Update product\n" +
                "6. Delete product\n");
        String command = scanner.nextLine();

        switch (command.toLowerCase()) {
            case "1":
                handleGetAllProducts();
                break;
            case "2":
                handleGetProductById(scanner);
                break;
            case "3":
                handleGetProductsByName(scanner);
                break;
            case "4":
                handleAddProduct(scanner);
                break;
            case "5":
                handleUpdateProduct(scanner);
                break;
            case "6":
                handleDeleteProduct(scanner);
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
        System.out.println();
    }

    private void handleGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product.toString() + "\n");
        }
    }

    private void handleGetProductById(Scanner scanner) {
        System.out.println("Enter the product ID:");
        String id = scanner.nextLine();

        Product product = productService.getProductById(id);
        if (product != null) {
            System.out.println("Product retrieved successfully:");
            System.out.println(product.toString());
        } else {
            System.out.println("Product not found");
        }
    }

    private void handleGetProductsByName(Scanner scanner) {
        System.out.println("Enter the product Name:");
        String name = scanner.nextLine();

        List<Product> products = productService.getProductsByName(name);
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product.toString() + "\n");
        }
    }

    private void handleAddProduct(Scanner scanner) throws IOException {
        System.out.println("Enter the product type (food, clothing):");
        String type = scanner.nextLine();

        System.out.println("Enter the product details:");
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Price:");
        Integer price = Integer.parseInt(scanner.nextLine());
        System.out.println("Status:");
        String statusString = scanner.nextLine();
        ProductStatus status = EnumHelpers.parseProductStatus(statusString);
        System.out.println("Description:");
        String description = scanner.nextLine();

        Product newProduct;
        if (type.equalsIgnoreCase("food")) {
            System.out.println("Enter the expiration date (yyyy-MM-dd):");
            String expirationDateString = scanner.nextLine();
            Date expirationDate = EnumHelpers.parseDate(expirationDateString);
            newProduct = new Food(price, status, name, description, expirationDate);
        } else if (type.equalsIgnoreCase("clothing")) {
            System.out.println("Enter the clothing size:");
            String sizeString = scanner.nextLine();
            ClothingSize size = EnumHelpers.parseClothingSize(sizeString);
            newProduct = new Clothing(price, status, name, description, size);
        } else {
            System.out.println("Invalid product type");
            return;
        }

        productService.addProduct(newProduct);
        System.out.println("Product added successfully");
    }

    private void handleUpdateProduct(Scanner scanner) throws IOException {
        System.out.println("Enter the product ID:");
        String id = scanner.nextLine();

        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            System.out.println("Product not found");
            return;
        }

        System.out.println("Enter the updated details for the product:");
        System.out.println("Name (current: " + existingProduct.getName() + "):");
        String name = scanner.nextLine();
        System.out.println("Price (current: " + existingProduct.getPrice() + "):");
        Integer price = Integer.parseInt(scanner.nextLine());
        System.out.println("Status (current: " + existingProduct.getStatus() + "):");
        String statusString = scanner.nextLine();
        ProductStatus status = EnumHelpers.parseProductStatus(statusString);
        System.out.println("Description (current: " + existingProduct.getDescription() + "):");
        String description = scanner.nextLine();

        Product updatedProduct;
        if (existingProduct instanceof Food) {
            System.out.println("Enter the updated expiration date (yyyy-MM-dd) (current: " + ((Food) existingProduct).getExpirationDate() + "):");
            String expirationDateString = scanner.nextLine();
            Date expirationDate = EnumHelpers.parseDate(expirationDateString);
            updatedProduct = new Food(price, status, name, description, expirationDate);
        } else if (existingProduct instanceof Clothing) {
            System.out.println("Enter the updated clothing size (current: " + ((Clothing) existingProduct).getSize() + "):");
            String sizeString = scanner.nextLine();
            ClothingSize size = EnumHelpers.parseClothingSize(sizeString);
            updatedProduct = new Clothing(price, status, name, description, size);
        } else {
            System.out.println("Invalid product type");
            return;
        }

        productService.updateProduct(id, updatedProduct);
        System.out.println("Product updated successfully");
    }

    private void handleDeleteProduct(Scanner scanner) {
        System.out.println("Enter the product ID:");
        String id = scanner.nextLine();

        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product not found");
        }
    }
}
