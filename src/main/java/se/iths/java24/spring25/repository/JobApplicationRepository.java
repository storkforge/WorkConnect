package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.JobApplication;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.entity.UserEntity;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    boolean existsByUserAndJob(UserEntity user, JobOpportunityEntity job);
}
