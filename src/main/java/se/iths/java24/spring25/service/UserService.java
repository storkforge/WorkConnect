package se.iths.java24.spring25.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.User;
import se.iths.java24.spring25.repository.UserRepository;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {
        // Check if username already exists
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(password);

        // Create user with default ROLE_USER
        User user = new User(username, encodedPassword, "ROLE_USER");

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Optional: Add method to update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Optional: Add method to delete user
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
