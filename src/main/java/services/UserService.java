package services;

import models.user.User;
import repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private static UserService instance;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static synchronized UserService getInstance(UserRepository userRepository) {
        if (instance == null) {
            instance = new UserService(userRepository);
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return this.userRepository.getAllItems();
    }

    public User getUserById(String id) {
        return this.userRepository.getUser(id);
    }

    public List<User> getUsersByName(String name) {
        return this.userRepository.getUsersByName(name);
    }

    public void addUser(User user) {
        this.userRepository.addUser(user);
    }

    public void updateUser(String id, User updatedUser) {
        User existingUser = this.userRepository.getUser(id);
        if (existingUser != null) {
            updatedUser.setId(existingUser.getId());
            this.userRepository.deleteUser(id);
            this.userRepository.addUser(updatedUser);
        }
    }

    public void deleteUser(String id) {
        this.userRepository.deleteUser(id);
    }
}
