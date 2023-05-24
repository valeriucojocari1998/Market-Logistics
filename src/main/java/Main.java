import controlers.ProductController;
import controlers.UserController;
import repositories.ProductRepository;
import repositories.UserRepository;
import services.ProductService;
import services.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String cosmosDbEndpoint = "https://market-logistics.documents.azure.com:443/";
    private static final String cosmosDbKey = "5dQONmJjMG7p6NJjk0fpD5YyYaOuopCymNruYZ4ni0xEZydEz9W121P0CQTiJqqQS6Wu3jRXVUHJACDb2ixuQA==";

    public static void main(String[] args) throws IOException {
        ProductRepository productRepository = ProductRepository.getInstance(cosmosDbEndpoint, cosmosDbKey);
        ProductService productService = ProductService.getInstance(productRepository);
        ProductController productController = ProductController.getInstance(productService);

        UserRepository userRepository = UserRepository.getInstance(cosmosDbEndpoint, cosmosDbKey);
        UserService userService = UserService.getInstance(userRepository);
        UserController userController = UserController.getInstance(userService);

        // Start console input handling
        handleConsoleInput(productController, userController);
    }

    public static void handleConsoleInput(ProductController productController, UserController userController) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Please input the action you would like to perform:\n" +
                    "1. Product Actions\n" +
                    "2. User Actions\n" +
                    "3. Exit\n");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "1":
                    productController.handleConsoleInputProduct(scanner);
                    break;
                case "2":
                    userController.handleConsoleInputUser(scanner);
                    break;
                case "3":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }

        scanner.close();
    }

}
