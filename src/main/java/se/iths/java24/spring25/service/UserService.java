package se.iths.java24.spring25.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.iths.java24.spring25.entity.AuthProvider;
import se.iths.java24.spring25.entity.Role;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String ABSTRACT_API_KEY = "...";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String name, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Email is not valid");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(name, email, hashedPassword, Role.USER);
        user.setProvider(AuthProvider.LOCAL);
        user.setProviderId(null);
        userRepository.save(user);
    }

    private boolean isValidEmail(String email) {
        boolean basicValid = email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        String apiKey = System.getenv("...");
        if (apiKey == null || apiKey.isEmpty()) {
            return basicValid;
        }

        try {
            String url = "https://emailvalidation.abstractapi.com/v1/?api_key=" + apiKey + "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode json = new ObjectMapper().readTree(response.body());
            return "DELIVERABLE".equalsIgnoreCase(json.path("deliverability").asText());

        } catch (Exception e) {
            return basicValid;
        }
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getABSTRACT_API_KEY() {
        return ABSTRACT_API_KEY;
    }
}
