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
 * Denna service hanterar laddning eller skapande av användare efter lyckad
 * autentisering med en OAuth2-leverantör (som Google eller Facebook).
 * Den kopplar OAuth2-användardata till vår applikations UserEntity.
 */
@Service // Markerar detta som en Spring Bean som kan injectas
public class CustomOAuth2UserService extends DefaultOAuth2UserService { // Vi ärver från Spring Securitys standard-service

    // Vi behöver tillgång till vår databas för att hitta/spara användare
    private final UserRepository userRepository;

    /**
     * Constructor Injection: Spring kommer automatiskt att skicka in
     * en instans av UserRepository när denna service skapas.
     * @param userRepository Repository för att interagera med UserEntity-data.
     */
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Denna metod anropas av Spring Security efter lyckad OAuth2-autentisering.
     * Den tar emot användarinformation från providern och ska returnera ett
     * OAuth2User-objekt som representerar användaren i VÅRT system.
     *
     * @param userRequest Innehåller information om klienten och access token.
     * @return Ett OAuth2User-objekt som Spring Security ska använda.
     * @throws OAuth2AuthenticationException Om något går fel.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println(">>> CustomOAuth2UserService.loadUser() CALLED!"); // Bra för felsökning

        // --- DEL 1: Hämta användardata från providern ---

        // Anropa standard-servicen för att få OAuth2User-objektet från Google/Facebook
        OAuth2User oauth2User = super.loadUser(userRequest);

        // Hämta attributen från OAuth2User-objektet
        Map<String, Object> attributes = oauth2User.getAttributes();

        // Försök hämta e-post (obligatoriskt för oss)
        String email = (String) attributes.get("email");
        if (email == null || email.isEmpty()) {
            // Om providern inte skickar med e-post kan vi inte hantera användaren
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        // Försök hämta namn
        String name = (String) attributes.get("name");

        // Hämta providerns unika ID för användaren (oftast 'sub' för Google, 'id' för Facebook)
        String providerId = oauth2User.getName(); // Standard är 'sub'/'id'

        // Identifiera vilken provider det är
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = AuthProvider.valueOf(registrationId.toUpperCase()); // Gör om "google" -> GOOGLE

        // (Utskrifter kan tas bort när allt funkar)
        // System.out.println("Email: " + email); System.out.println("Name: " + name); ...

        // --- DEL 2: Hitta eller skapa UserEntity ---

        // Försök hitta användaren i vår databas via e-post
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        UserEntity user; // Variabel för att hålla vår lokala UserEntity

        if (userOptional.isPresent()) {
            // Användare finns redan
            user = userOptional.get();
            System.out.println("Found existing user: " + email);

            // Kontrollera om användaren försöker logga in med en annan metod än den hen skapades med
            if (!user.getProvider().equals(provider)) {
                // T.ex. om ett lokalt konto med samma e-post redan finns.
                // Du kan välja att länka konton här (mer avancerat) eller neka inloggning.
                throw new OAuth2AuthenticationException("User with email " + email + " already exists with provider " + user.getProvider() + ". Cannot login with " + provider + ".");
            }
            // Här skulle man kunna uppdatera användarens namn om det ändrats hos providern
            // if (name != null && !name.equals(user.getName())) { user.setName(name); userRepository.save(user); }

        } else {
            // Användare finns inte - skapa en ny i vår databas
            System.out.println("Creating new user for: " + email);
            user = new UserEntity();
            user.setEmail(email);
            user.setName(name); // Sätt namnet från providern
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setPassword(null); // OAuth2-användare har inget lokalt lösenord hos oss

            userRepository.save(user); // Spara den nya användaren
            System.out.println("Saved new user: " + email + " with provider " + provider);
        }

        // --- DEL 3: Returnera Principal (användarobjekt) till Spring Security ---

        // Hämta nödvändiga authorities (roller). Just nu tomt.
        // TODO: Implementera korrekt rollhantering baserat på UserEntity senare.
        // Exempel: Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        // Skapa och returnera ett DefaultOAuth2User-objekt.
        // Sista argumentet ('email') talar om för Spring Security att använda
        // värdet från 'email'-attributet som användarens "namn" (principal name).
        return new DefaultOAuth2User(
                Collections.emptyList(), // Skicka med roller här sen (List<GrantedAuthority>)
                attributes,              // Originalattributen från providern
                "email"                  // <<< Använd 'email' som "namn"-attribut!
        );
    }
}
