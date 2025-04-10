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
                        .requestMatchers("/public/**", "/register", "/login", "/error", "/", "/css/**", "/js/**", "/images/**", "/download.png").permitAll() // <-- Lade till vanliga statiska resurser och din logo
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/dashboard", true)
                                //.failureUrl("/login?error=true") // If needed: error.html, needs to be implemented
                )
                .oauth2Login(oauth2 -> oauth2
                                .loginPage("/login")
                                .defaultSuccessUrl("/dashboard", true)
                        // .failureUrl("/login?error=true") //
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-success?logout")
                        .permitAll()
                );

        return http.build();
    }
}
