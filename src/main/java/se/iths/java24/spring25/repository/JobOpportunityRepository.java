package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

import java.util.List;
import java.util.Optional;

public interface JobOpportunityRepository extends JpaRepository<JobOpportunityEntity, Long> {
    // Implement custom queries if needed
    Optional<JobOpportunityEntity> findByJobTitle(String jobTitle); // Add this method
//    List<JobOpportunityEntity> findByLocation(String location); // Add this method
//    List<JobOpportunityEntity> findByDescription(String description); // Add this method
//    List<JobOpportunityEntity> findByEvent(EventEntity event); // Add this method
}
