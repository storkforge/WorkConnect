package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

@Component
class JobOpportunityMapper {
    JobOpportunityDTO map(JobOpportunityEntity jobOpportunityEntity) {
        JobOpportunityDTO dto = new JobOpportunityDTO();
        // map all fields
        return dto;
    }

    JobOpportunityEntity map(JobOpportunityDTO dto) {
        JobOpportunityEntity jobOpportunityEntity = new JobOpportunityEntity();
        // map all fields
        return jobOpportunityEntity;
    }
}

