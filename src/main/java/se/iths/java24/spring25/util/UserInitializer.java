package se.iths.java24.spring25.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create admin user
        if (userRepository.findByName("admin").isEmpty()) {
            UserEntity admin = new UserEntity(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    "ROLE_ADMIN"
            );
            userRepository.save(admin);
        }

        // Create regular user
        if (userRepository.findByName("user").isEmpty()) {
            UserEntity user = new UserEntity(
                    "user",
                    passwordEncoder.encode("user123"),
                    "ROLE_USER"
            );
            userRepository.save(user);
        }
    }
}
