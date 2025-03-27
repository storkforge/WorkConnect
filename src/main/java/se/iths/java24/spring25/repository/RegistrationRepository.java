package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Implement custom queries if needed
}
