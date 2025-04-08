package se.iths.java24.spring25;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.repository.InternshipRepository;
import se.iths.java24.spring25.service.InternshipService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InternshipServiceTests {

    @Mock
    private InternshipRepository internshipRepository; // Mocking the repository

    @InjectMocks
    private InternshipService internshipService; // Injecting mock repository into the service

    private InternshipEntity internship;

    @BeforeEach
    public void setUp() {
        internship = new InternshipEntity();
        internship.setId(1L);
        internship.setDescription("Sample Internship");
    }

    @Test
    public void testCreateInternship() {
        // When the repository's save method is called, return the internship
        when(internshipRepository.save(any(InternshipEntity.class))).thenReturn(internship);

        // Call the service method
        InternshipEntity createdInternship = internshipService.createInternship(internship);

        // Verify that the service method returns the correct entity
        assertNotNull(createdInternship);
        assertEquals(1L, createdInternship.getId());
        assertEquals("Sample Internship", createdInternship.getDescription());

        // Verify that the repository's save method was called
        verify(internshipRepository, times(1)).save(internship);
    }

    @Test
    public void testGetInternshipById() {
        // Mock the repository's behavior for findById
        when(internshipRepository.findById(1L)).thenReturn(Optional.of(internship));

        // Call the service method
        Optional<InternshipEntity> foundInternship = internshipService.getInternshipById(1L);

        // Assert the returned internship is the one we mocked
        assertTrue(foundInternship.isPresent());
        assertEquals(1L, foundInternship.get().getId());
        assertEquals("Sample Internship", foundInternship.get().getDescription());

        // Verify that the repository's findById method was called once
        verify(internshipRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteInternship() {
        // Call the service method to delete an internship
        internshipService.deleteInternship(1L);

        // Verify that deleteById was called once on the repository
        verify(internshipRepository, times(1)).deleteById(1L);
    }
}
