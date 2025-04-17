package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

import static org.junit.jupiter.api.Assertions.*;

class JobOpportunityMapperTest {

    private final JobOpportunityMapper jobOpportunityMapper = new JobOpportunityMapper();

    @Test
    void testMapToDTO() {
        // Arrange
        JobOpportunityEntity entity = new JobOpportunityEntity();
        entity.setId(1L);
        entity.setDescription("Remote Java Developer");

        // Act
        JobOpportunityDTO dto = jobOpportunityMapper.map(entity);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Remote Java Developer", dto.description());
    }

    @Test
    void testMapToEntity() {
        // Arrange
        JobOpportunityDTO dto = new JobOpportunityDTO(null, "Cloud Engineer");

        // Act
        JobOpportunityEntity entity = jobOpportunityMapper.map(dto);

        // Assert
        assertNull(entity.getId()); // ID is intentionally not mapped
        assertEquals("Cloud Engineer", entity.getDescription());
    }
}