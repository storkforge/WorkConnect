package se.iths.java24.spring25.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.dto.JobOpportunityDTO;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.repository.JobOpportunityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobOpportunityService {

    @Autowired
    private JobOpportunityMapper jobOpportunityMapper;

    @Autowired
    private JobOpportunityRepository jobOpportunityRepository;

    public JobOpportunityDTO createJobOpportunity(JobOpportunityDTO jobOpportunityDTO) {
        JobOpportunityEntity jobOpportunity = jobOpportunityRepository.save(jobOpportunityMapper.map(jobOpportunityDTO));
        return jobOpportunityMapper.map(jobOpportunity);
    }

    public Optional<JobOpportunityDTO> getJobOpportunityById(Long id) {
        return jobOpportunityRepository.findById(id).map(jobOpportunityMapper::map);
    }

    public List<JobOpportunityDTO> getAllJobOpportunities() {
        return jobOpportunityRepository.findAll().stream().map(jobOpportunityMapper::map).toList();
    }

    public JobOpportunityDTO updateJobOpportunity(JobOpportunityDTO jobOpportunityDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteJobOpportunityById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

