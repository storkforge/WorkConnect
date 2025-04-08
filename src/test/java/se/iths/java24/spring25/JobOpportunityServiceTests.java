package se.iths.java24.spring25;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.repository.JobOpportunityRepository;
import se.iths.java24.spring25.service.JobOpportunityService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class JobOpportunityServiceTests {

    @Mock
    private JobOpportunityRepository jobOpportunityRepository;

    @InjectMocks
    private JobOpportunityService jobOpportunityService;

    public JobOpportunityServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllJobOpportunities() {
        JobOpportunityEntity opportunity1 = new JobOpportunityEntity();
        JobOpportunityEntity opportunity2 = new JobOpportunityEntity();
        when(jobOpportunityRepository.findAll()).thenReturn(Arrays.asList(opportunity1, opportunity2));

        List<JobOpportunityEntity> result = jobOpportunityService.getAllJobOpportunities();

        assertThat(result).hasSize(2);
        verify(jobOpportunityRepository, times(1)).findAll();
    }

    @Test
    void testGetJobOpportunityById() {
        JobOpportunityEntity opportunity = new JobOpportunityEntity();
        opportunity.setId(1L);
        when(jobOpportunityRepository.findById(1L)).thenReturn(Optional.of(opportunity));

        Optional<JobOpportunityEntity> result = jobOpportunityService.getJobOpportunityById(1L);

        assertThat(result).isPresent().contains(opportunity);
        verify(jobOpportunityRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateJobOpportunity() {
        JobOpportunityEntity opportunity = new JobOpportunityEntity();
        opportunity.setDescription("New opportunity");
        when(jobOpportunityRepository.save(opportunity)).thenReturn(opportunity);

        JobOpportunityEntity result = jobOpportunityService.createJobOpportunity(opportunity);

        assertThat(result.getDescription()).isEqualTo("New opportunity");
        verify(jobOpportunityRepository, times(1)).save(opportunity);
    }

    @Test
    void testDeleteJobOpportunity() {
        Long id = 1L;

        jobOpportunityService.deleteJobOpportunity(id);

        verify(jobOpportunityRepository, times(1)).deleteById(id);
    }
}
