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

    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // Use BCrypt for secure password hashing
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        // Configure custom authentication provider
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return new ProviderManager(authProvider);
//    }
}
