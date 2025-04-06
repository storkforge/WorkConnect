package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunityEntity, Long> {
    // Implement custom queries if needed
}
