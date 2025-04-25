package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.InternshipEntity;

@Component
class InternshipMapper {
    InternshipDTO map(InternshipEntity internshipEntity) {
        return new InternshipDTO(
                internshipEntity.getId(),
                internshipEntity.getTitle(),
                internshipEntity.getCompany(),
                internshipEntity.getLocation(),
                internshipEntity.getDescription(),
                internshipEntity.getStartDate(),
                internshipEntity.getEndDate()
        );
    }

    InternshipEntity map(InternshipDTO dto) {
        InternshipEntity internshipEntity = new InternshipEntity();
        // Do not map id
        internshipEntity.setTitle(dto.title());
        internshipEntity.setCompany(dto.company());
        internshipEntity.setLocation(dto.location());
        internshipEntity.setDescription(dto.description());
        internshipEntity.setStartDate(dto.startDate());
        internshipEntity.setEndDate(dto.endDate());
        return internshipEntity;
    }
}
