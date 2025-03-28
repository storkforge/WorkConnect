package se.iths.java24.spring25.domain;


import org.springframework.stereotype.Component;
import se.iths.java24.spring25.dto.InternshipDTO;
import se.iths.java24.spring25.entity.InternshipEntity;

@Component
public class InternshipMapper {
    public InternshipDTO map(InternshipEntity internshipEntity) {
        InternshipDTO dto = new InternshipDTO();
        // map all fields
        return dto;
    }

    public InternshipEntity map(InternshipDTO dto) {
        InternshipEntity internshipEntity = new InternshipEntity();
        // map all fields
        return internshipEntity;
    }
}