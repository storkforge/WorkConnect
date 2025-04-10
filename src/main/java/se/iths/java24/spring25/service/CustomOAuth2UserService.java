package se.iths.java24.spring25.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.AuthProvider;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * This service handles loading or creating users after successful
 * authentication with an OAuth2 provider (such as Google or Facebook).
 * It connects OAuth2 user data to our application's UserEntity.
 */
@Service // Markerar detta som en Spring Bean som kan injectas
public class CustomOAuth2UserService extends DefaultOAuth2UserService { // Vi ärver från Spring Securitys standard-service
    // We need access to our database to find/save users
    private final UserRepository userRepository;

    /**
     * Constructor Injection: Spring will automatically inject
     * an instance of UserRepository when this service is created.
     *
     * @param userRepository Repository for interacting with UserEntity data.
     */
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is called by Spring Security after successful OAuth2 authentication.
     * It receives user information from the provider and should return an
     * OAuth2User object that represents the user in OUR system.
     *
     * @param userRequest Contains information about the client and access token.
     * @return An OAuth2User object that Spring Security will use.
     * @throws OAuth2AuthenticationException If something goes wrong.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println(">>> CustomOAuth2UserService.loadUser() CALLED!"); // Good for debugging

        // --- PART 1: Get user data from the provider ---

        // Call the standard service to get the OAuth2User object from Google/Facebook
        OAuth2User oauth2User = super.loadUser(userRequest);

        // Get attributes from the OAuth2User object
        Map<String, Object> attributes = oauth2User.getAttributes();

        // Try to get email (mandatory for us)
        String email = (String) attributes.get("email");
        if (email == null || email.isEmpty()) {
            // If the provider doesn't include an email, we can't handle the user
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        // Try to get name
        String name = (String) attributes.get("name");

        // Get the provider's unique ID for the user (usually 'sub' for Google, 'id' for Facebook)
        String providerId = oauth2User.getName(); // Standard is 'sub'/'id'

        // Identify which provider it is
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = AuthProvider.valueOf(registrationId.toUpperCase()); // Convert "google" -> GOOGLE

        // (Prints can be removed when everything works)
        // System.out.println("Email: " + email); System.out.println("Name: " + name); ...

        // --- PART 2: Find or create UserEntity ---

        // Try to find the user in our database via email
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        UserEntity user; // Variable to hold our local UserEntity

        if (userOptional.isPresent()) {
            // User already exists
            user = userOptional.get();
            System.out.println("Found existing user: " + email);

            // Check if the user is trying to log in with a different method than the one they were created with
            if (!user.getProvider().equals(provider)) {
                // E.g., if a local account with the same email already exists.
                // You can choose to link accounts here (more advanced) or deny login.
                throw new OAuth2AuthenticationException("User with email " + email + " already exists with provider " + user.getProvider() + ". Cannot login with " + provider + ".");
            }
            // Here you could update the user's name if it has changed at the provider
            // if (name != null && !name.equals(user.getName())) { user.setName(name); userRepository.save(user); }

        } else {
            // User does not exist - create a new one in our database
            System.out.println("Creating new user for: " + email);
            user = new UserEntity();
            user.setEmail(email);
            user.setName(name); // Set the name from the provider
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setPassword(null); // OAuth2 users don't have a local password with us

            userRepository.save(user); // Save the new user
            System.out.println("Saved new user: " + email + " with provider " + provider);
        }

        // --- PART 3: Return Principal (user object) to Spring Security ---

        // Get necessary authorities (roles). Currently empty.
        // TODO: Implement proper role management based on UserEntity later.
        // Example: Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        // Create and return a DefaultOAuth2User object.
        // The last argument ('email') tells Spring Security to use
        // the value from the 'email' attribute as the user's "name" (principal name).
        return new DefaultOAuth2User(
                Collections.emptyList(), // Include roles here later (List<GrantedAuthority>)
                attributes,              // Original attributes from the provider
                "email"                  // <<< Use 'email' as the "name" attribute!
        );
    }
}
