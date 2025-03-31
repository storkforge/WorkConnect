package se.iths.java24.spring25.domain;


import org.springframework.stereotype.Component;
import se.iths.java24.spring25.dto.JobOpportunityDTO;
import se.iths.java24.spring25.entity.JobOpportunityEntity;


@Component
public class JobOpportunityMapper {
    public JobOpportunityDTO map(JobOpportunityEntity jobOpportunityEntity) {
        JobOpportunityDTO dto = new JobOpportunityDTO();
        // map all fields
        return dto;
    }

    public JobOpportunityEntity map(JobOpportunityDTO dto) {
        JobOpportunityEntity jobOpportunityEntity = new JobOpportunityEntity();
        // map all fields
        return jobOpportunityEntity;
    }
}

