package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.*;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    boolean existsByUserAndJob(UserEntity user, JobOpportunityEntity job);
}
