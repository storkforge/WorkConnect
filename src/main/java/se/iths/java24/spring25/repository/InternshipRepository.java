package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.InternshipEntity;

public interface InternshipRepository extends JpaRepository<InternshipEntity, Long> {
    // Implement custom queries if needed
}
