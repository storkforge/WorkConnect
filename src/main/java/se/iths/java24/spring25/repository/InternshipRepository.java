package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    // Implement custom queries if needed
}
