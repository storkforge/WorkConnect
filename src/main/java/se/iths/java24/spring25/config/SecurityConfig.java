package se.iths.java24.spring25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/register", "/login", "/error", "/").permitAll() // Public endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/dashboard", true) // Redirect after successful login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirect after logout
                );

        return http.build();
    }
}