package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import se.iths.java24.spring25.entity.InternshipEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InternshipMapperTest {

    private final InternshipMapper internshipMapper = new InternshipMapper();

    @Test
    void testMapToDTO() {
        // Arrange
        InternshipEntity entity = new InternshipEntity();
        entity.setId(1L);
        entity.setTitle("Backend Internship");
        entity.setCompany("TechCorp");
        entity.setLocation("Berlin");
        entity.setDescription("Learn backend development");
        entity.setStartDate(LocalDate.of(2025, 6, 1));
        entity.setEndDate(LocalDate.of(2025, 9, 1));

        // Act
        InternshipDTO dto = internshipMapper.map(entity);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Backend Internship", dto.title());
        assertEquals("TechCorp", dto.company());
        assertEquals("Berlin", dto.location());
        assertEquals("Learn backend development", dto.description());
        assertEquals(LocalDate.of(2025, 6, 1), dto.startDate());
        assertEquals(LocalDate.of(2025, 9, 1), dto.endDate());
    }

    @Test
    void testMapToEntity() {
        // Arrange
        InternshipDTO dto = new InternshipDTO(
                null,
                "Frontend Internship",
                "WebCo",
                "Amsterdam",
                "Work with React and Angular",
                LocalDate.of(2025, 5, 15),
                LocalDate.of(2025, 8, 15)
        );

        // Act
        InternshipEntity entity = internshipMapper.map(dto);

        // Assert
        assertNull(entity.getId()); // ID is not mapped
        assertEquals("Frontend Internship", entity.getTitle());
        assertEquals("WebCo", entity.getCompany());
        assertEquals("Amsterdam", entity.getLocation());
        assertEquals("Work with React and Angular", entity.getDescription());
        assertEquals(LocalDate.of(2025, 5, 15), entity.getStartDate());
        assertEquals(LocalDate.of(2025, 8, 15), entity.getEndDate());
    }
}