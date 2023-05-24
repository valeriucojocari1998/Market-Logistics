package controlers;

import models.user.Customer;
import models.user.Manager;
import models.user.User;
import services.ProductService;
import services.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserController {

    private final UserService userService;
    private static UserController instance;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    public static synchronized UserController getInstance(UserService userService) {
        if (instance == null) {
            instance = new UserController(userService);
        }

        return instance;
    }

    public void handleConsoleInputUser(Scanner scanner) throws IOException {
        System.out.println("Please input the action you would like to perform:\n" +
                "1. Get all users\n" +
                "2. Get user by Id\n" +
                "3. Get users by Name\n" +
                "4. Add a user\n" +
                "5. Delete user\n");
        String command = scanner.nextLine();

        switch (command.toLowerCase()) {
            case "1":
                handleGetAllUsers();
                break;
            case "2":
                handleGetUserById(scanner);
                break;
            case "3":
                handleGetUsersByName(scanner);
                break;
            case "4":
                handleAddUser(scanner);
                break;
            case "5":
                handleDeleteUser(scanner);
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
        System.out.println();
    }

    private void handleGetAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user.toString() + "\n");
        }
    }

    private void handleGetUserById(Scanner scanner) {
        System.out.println("Enter the user ID:");
        String id = scanner.nextLine();

        User user = userService.getUserById(id);
        if (user != null) {
            System.out.println("User retrieved successfully:");
            System.out.println(user);
        } else {
            System.out.println("User not found");
        }
    }

    private void handleGetUsersByName(Scanner scanner) {
        System.out.println("Enter the user Name:");
        String name = scanner.nextLine();

        List<User> users = userService.getUsersByName(name);
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user.toString() + "\n");
        }
    }

    private void handleAddUser(Scanner scanner) throws IOException {
        System.out.println("Please input the user type:\n" +
                "1. Manager\n" +
                "2. Customer\n");
        String userType = scanner.nextLine();

        switch (userType) {
            case "1":
                handleManagerInput(scanner);
                break;
            case "2":
                handleCustomerInput(scanner);
                break;
            default:
                System.out.println("Invalid user type");
                break;
        }
    }

    private void handleManagerInput(Scanner scanner) {
        System.out.println("Enter the manager's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the manager's age:");
        Integer age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the manager's enrolment date (yyyy-MM-dd):");
        String enrolmentDateString = scanner.nextLine();
        System.out.println("Enter the manager's salary:");
        Integer salary = Integer.parseInt(scanner.nextLine());

        Manager manager = new Manager(name, age, salary);
        userService.addUser(manager);
    }

    private void handleCustomerInput(Scanner scanner) {
        System.out.println("Enter the customer's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the customer's age:");
        Integer age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the customer's address:");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, age, address);

        userService.addUser(customer);    }


    private void handleDeleteUser(Scanner scanner) {
        System.out.println("Enter the user ID:");
        String id = scanner.nextLine();

        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User not found");
        }
    }
}
