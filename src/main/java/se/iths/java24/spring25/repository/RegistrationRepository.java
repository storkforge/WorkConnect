package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.RegistrationEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
    // Implement custom queries if needed
}
