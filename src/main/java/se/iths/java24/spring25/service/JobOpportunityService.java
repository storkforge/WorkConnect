package se.iths.java24.spring25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.JobOpportunity;
import se.iths.java24.spring25.repository.JobOpportunityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobOpportunityService {

    @Autowired
    private JobOpportunityRepository jobOpportunityRepository;

    public List<JobOpportunity> getAllJobOpportunities() {
        return jobOpportunityRepository.findAll();
    }

    public Optional<JobOpportunity> getJobOpportunityById(Long id) {
        return jobOpportunityRepository.findById(id);
    }

    public JobOpportunity createJobOpportunity(JobOpportunity jobOpportunity) {
        return jobOpportunityRepository.save(jobOpportunity);
    }

    public void deleteJobOpportunity(Long id) {
        jobOpportunityRepository.deleteById(id);
    }
}
