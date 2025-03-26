package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.JobOpportunity;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunity, Long> {
    // Implement custom queries if needed
}
