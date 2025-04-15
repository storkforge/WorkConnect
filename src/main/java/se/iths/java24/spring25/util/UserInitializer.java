package se.iths.java24.spring25.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.AuthProvider;
import se.iths.java24.spring25.entity.Role;
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
        // Admin user
        if (userRepository.findByEmail("admin@workconnect.com").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setName("admin");
            admin.setEmail("admin@workconnect.com");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.setRole(Role.ADMIN);
            admin.setProvider(AuthProvider.LOCAL);
            admin.setProviderId(null);
            userRepository.save(admin);
        }

        // Regular user
        if (userRepository.findByEmail("user@workconnect.com").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setName("user");
            user.setEmail("user@workconnect.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(Role.USER);
            user.setProvider(AuthProvider.LOCAL);
            user.setProviderId(null);
            userRepository.save(user);
        }
    }
}
