package se.iths.java24.spring25.service;

import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.repository.JobOpportunityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobOpportunityService {

    private final JobOpportunityRepository jobOpportunityRepository;

    public JobOpportunityService(JobOpportunityRepository jobOpportunityRepository) {
        this.jobOpportunityRepository = jobOpportunityRepository;
    }

    public List<JobOpportunityEntity> getAllJobOpportunities() {
        return jobOpportunityRepository.findAll();
    }

    public Optional<JobOpportunityEntity> getJobOpportunityById(Long id) {
        return jobOpportunityRepository.findById(id);
    }

    public JobOpportunityEntity createJobOpportunity(JobOpportunityEntity jobOpportunity) {
        return jobOpportunityRepository.save(jobOpportunity);
    }

    public void deleteJobOpportunity(Long id) {
        jobOpportunityRepository.deleteById(id);
    }
}
