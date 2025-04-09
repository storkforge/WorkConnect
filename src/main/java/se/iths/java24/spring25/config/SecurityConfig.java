package se.iths.java24.spring25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Glöm inte att lägga till en PasswordEncoder Bean om du inte redan har det!
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/register", "/login", "/error", "/", "/css/**", "/js/**", "/images/**", "/download.png").permitAll() // <-- Lade till vanliga statiska resurser och din logo
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/dashboard", true)
                                //.failureUrl("/login?error=true") // If needed: error.html, needs to be implemented
                )
                .oauth2Login(oauth2 -> oauth2
                                .loginPage("/login") // Denna är nu lite redundant men skadar inte
                                .defaultSuccessUrl("/dashboard", true)
                        // .failureUrl("/login?error=true") // Valfritt: Vart man ska vid misslyckad OAuth2
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Standard URL för att initiera utloggning (POST)
                        .logoutSuccessUrl("/logout-success?logout") // Omdirigera till en GET endpoint efter lyckad utloggning
                        .permitAll() // Tillåt alla att komma åt /logout
                );

        return http.build();
    }

    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Måste finnas för att formLogin ska fungera med lösenord
        return new BCryptPasswordEncoder();
    }
    */
}
