package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.*;

import java.util.List;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    boolean existsByUserAndJob(UserEntity user, JobOpportunityEntity job);
    List<SavedJob> findByUser(UserEntity user);

}
