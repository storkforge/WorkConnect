package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.service.InternshipService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InternshipControllerTest {

    @Mock
    private InternshipMapper internshipMapper;

    @Mock
    private InternshipService internshipService;

    @InjectMocks
    private InternshipController internshipController;

    @Test
    void testCreateInternship() {
        InternshipDTO inputDto = new InternshipDTO(
                null,
                "Java Internship",
                "OpenAI",
                "Remote",
                "Working with Java",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 8, 1)
        );

        InternshipEntity entity = new InternshipEntity();
        InternshipEntity savedEntity = new InternshipEntity();
        savedEntity.setId(1L);
        savedEntity.setTitle("Java Internship");
        savedEntity.setCompany("OpenAI");
        savedEntity.setLocation("Remote");
        savedEntity.setDescription("Working with Java");
        savedEntity.setStartDate(LocalDate.of(2025, 5, 1));
        savedEntity.setEndDate(LocalDate.of(2025, 8, 1));

        InternshipDTO savedDto = new InternshipDTO(
                1L,
                "Java Internship",
                "OpenAI",
                "Remote",
                "Working with Java",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 8, 1)
        );

        when(internshipMapper.map(inputDto)).thenReturn(entity);
        when(internshipService.createInternship(entity)).thenReturn(savedEntity);
        when(internshipMapper.map(savedEntity)).thenReturn(savedDto);

        ResponseEntity<InternshipDTO> response = internshipController.createInternship(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
    }
    @Test
    void testUpdateInternship() {
        // Arrange
        Long id = 1L;
        InternshipDTO dto = new InternshipDTO(
                null,
                "Updated Internship",
                "Updated Company",
                "Remote",
                "Updated description",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 9, 1)
        );

        InternshipEntity entity = new InternshipEntity();
        entity.setId(id); // set manually, since controller does this

        when(internshipMapper.map(dto)).thenReturn(entity);

        // Act
        ResponseEntity<Void> response = internshipController.updateInternship(id, dto);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(internshipService).updateInternship(entity);
        assertEquals(id, entity.getId());
    }

    @Test
    void testGetAllInternships() {
        // Arrange
        InternshipEntity entity = new InternshipEntity();
        entity.setId(1L);
        entity.setTitle("Spring Boot Internship");
        entity.setCompany("Spring Co.");
        entity.setLocation("Remote");
        entity.setDescription("A great internship to learn Spring Boot.");
        entity.setStartDate(LocalDate.of(2025, 7, 15));
        entity.setEndDate(LocalDate.of(2025, 9, 15));

        InternshipDTO dto = new InternshipDTO(
                1L,
                "Spring Boot Internship",
                "Spring Co.",
                "Remote",
                "A great internship to learn Spring Boot.",
                LocalDate.of(2025, 7, 15),
                LocalDate.of(2025, 9, 15)
        );

        when(internshipService.getAllInternships()).thenReturn(List.of(entity));
        when(internshipMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<List<InternshipDTO>> response = internshipController.getAllInternships();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().getFirst());
    }

    @Test
    void testGetInternshipById_Found() {
        // Arrange
        Long id = 1L;
        InternshipEntity entity = new InternshipEntity();
        entity.setId(id);
        entity.setTitle("Backend Internship");
        entity.setCompany("Tech Corp");
        entity.setLocation("Stockholm");
        entity.setDescription("Learn real-world backend development.");
        entity.setStartDate(LocalDate.of(2025, 8, 10));
        entity.setEndDate(LocalDate.of(2025, 10, 10));

        InternshipDTO dto = new InternshipDTO(
                id,
                "Backend Internship",
                "Tech Corp",
                "Stockholm",
                "Learn real-world backend development.",
                LocalDate.of(2025, 8, 10),
                LocalDate.of(2025, 10, 10)
        );

        when(internshipService.getInternshipById(id)).thenReturn(Optional.of(entity));
        when(internshipMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<InternshipDTO> response = internshipController.getInternshipById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetInternshipById_NotFound() {
        // Arrange
        Long id = 99L;
        when(internshipService.getInternshipById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<InternshipDTO> response = internshipController.getInternshipById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteInternshipById() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = internshipController.deleteInternshipById(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(internshipService).deleteInternship(id);
    }
}