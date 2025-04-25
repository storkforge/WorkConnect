package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

@Component
class JobOpportunityMapper {
    JobOpportunityDTO map(JobOpportunityEntity jobOpportunityEntity) {
        return new JobOpportunityDTO(
                jobOpportunityEntity.getId(),
                jobOpportunityEntity.getDescription()
        );
    }

    JobOpportunityEntity map(JobOpportunityDTO dto) {
        JobOpportunityEntity jobOpportunityEntity = new JobOpportunityEntity();
        // do not map id
        jobOpportunityEntity.setDescription(dto.description());
        return jobOpportunityEntity;
    }
}