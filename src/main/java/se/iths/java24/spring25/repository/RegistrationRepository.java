package se.iths.java24.spring25.repository;

import jakarta.servlet.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Implement custom queries if needed
}
