package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.service.JobOpportunityService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobOpportunityControllerTest {

    @Mock
    private JobOpportunityService jobOpportunityService;

    @Mock
    private JobOpportunityMapper jobOpportunityMapper;

    @InjectMocks
    private JobOpportunityController jobOpportunityController;

    @Test
    void testCreateJobOpportunity() {
        // Arrange
        JobOpportunityDTO inputDto = new JobOpportunityDTO(null, "Full-stack Developer Position");
        JobOpportunityEntity entity = new JobOpportunityEntity();
        JobOpportunityEntity savedEntity = new JobOpportunityEntity();
        savedEntity.setId(1L);
        savedEntity.setDescription("Full-stack Developer Position");

        JobOpportunityDTO savedDto = new JobOpportunityDTO(1L, "Full-stack Developer Position");

        when(jobOpportunityMapper.map(inputDto)).thenReturn(entity);
        when(jobOpportunityService.createJobOpportunity(entity)).thenReturn(savedEntity);
        when(jobOpportunityMapper.map(savedEntity)).thenReturn(savedDto);

        // Act
        ResponseEntity<JobOpportunityDTO> response = jobOpportunityController.createJobOpportunity(inputDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
    }

    @Test
    void testUpdateJobOpportunity() {
        // Arrange
        Long id = 1L;
        JobOpportunityDTO dto = new JobOpportunityDTO(null, "Updated Description");
        JobOpportunityEntity entity = new JobOpportunityEntity();

        when(jobOpportunityMapper.map(dto)).thenReturn(entity);

        // Act
        ResponseEntity<Void> response = jobOpportunityController.updateJobOpportunity(id, dto);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(jobOpportunityService).updateJobOpportunity(entity);
        assertEquals(id, entity.getId());
    }

    @Test
    void testGetAllJobOpportunities() {
        // Arrange
        JobOpportunityEntity entity = new JobOpportunityEntity();
        entity.setId(1L);
        entity.setDescription("Backend Engineer");

        JobOpportunityDTO dto = new JobOpportunityDTO(1L, "Backend Engineer");

        when(jobOpportunityService.getAllJobOpportunities()).thenReturn(List.of(entity));
        when(jobOpportunityMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<List<JobOpportunityDTO>> response = jobOpportunityController.getAllJobOpportunities();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void testGetJobOpportunityById_Found() {
        // Arrange
        Long id = 1L;
        JobOpportunityEntity entity = new JobOpportunityEntity();
        entity.setId(id);
        entity.setDescription("DevOps Engineer");

        JobOpportunityDTO dto = new JobOpportunityDTO(id, "DevOps Engineer");

        when(jobOpportunityService.getJobOpportunityById(id)).thenReturn(Optional.of(entity));
        when(jobOpportunityMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<JobOpportunityDTO> response = jobOpportunityController.getJobOpportunityById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetJobOpportunityById_NotFound() {
        // Arrange
        Long id = 42L;
        when(jobOpportunityService.getJobOpportunityById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<JobOpportunityDTO> response = jobOpportunityController.getJobOpportunityById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteJobOpportunityById() {
        // Arrange
        Long id = 10L;

        // Act
        ResponseEntity<Void> response = jobOpportunityController.deleteJobOpportunityById(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(jobOpportunityService).deleteJobOpportunity(id);
    }
}