package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Component;
import se.iths.java24.spring25.entity.InternshipEntity;

@Component
class InternshipMapper {
    InternshipDTO map(InternshipEntity internshipEntity) {
        InternshipDTO dto = new InternshipDTO();
        // map all fields
        return dto;
    }

    InternshipEntity map(InternshipDTO dto) {
        InternshipEntity internshipEntity = new InternshipEntity();
        // map all fields
        return internshipEntity;
    }
}

