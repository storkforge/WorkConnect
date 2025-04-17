package se.iths.java24.spring25.service;

import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.AuthProvider;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.entity.Role;
import se.iths.java24.spring25.entity.UserEntity;
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

    public JobOpportunityEntity updateJobOpportunity(JobOpportunityEntity jobOpportunity) {
        return jobOpportunityRepository.save(jobOpportunity);
    }

    public void registerNewJob(String company_name, String job_title, String job_description, String location, String terms_of_employment) {
        if (company_name == null || company_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (job_title == null || job_title.trim().isEmpty()) {
            throw new IllegalArgumentException("Jobtitle cannot be empty");
        }

        if (job_description == null || job_description.trim().isEmpty()) {
            throw new IllegalArgumentException("Job description cannot be empty");
        }

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        if (terms_of_employment == null || terms_of_employment.trim().isEmpty()) {
            throw new IllegalArgumentException("Terms Of Employment cannot be empty");
        }

        JobOpportunityEntity job = new JobOpportunityEntity(company_name, job_title, job_description, location, terms_of_employment);
        job.setProvider(AuthProvider.LOCAL);
        job.setProviderId(null);
        jobOpportunityRepository.save(job);
    }
}
