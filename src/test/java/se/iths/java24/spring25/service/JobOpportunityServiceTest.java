package se.iths.java24.spring25.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.repository.JobOpportunityRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobOpportunityServiceTest {

    private JobOpportunityRepository repository;
    private JobOpportunityService service;

    @BeforeEach
    void setUp() {
        repository = mock(JobOpportunityRepository.class);
        service = new JobOpportunityService(repository);
    }

    @Test
    void shouldReturnAllJobOpportunities() {
        JobOpportunityEntity job1 = new JobOpportunityEntity("Company A", "Dev", "Build stuff", "Gbg", "Full-time");
        JobOpportunityEntity job2 = new JobOpportunityEntity("Company B", "Tester", "Break Stuff", "Malmö", "Part-time");

        when(repository.findAll()).thenReturn(Arrays.asList(job1, job2));

        List<JobOpportunityEntity> result = service.getAllJobOpportunities();

        assertThat(result).hasSize(2).contains(job1, job2);
    }

    @Test
    void shouldReturnJobOpportunityById() {
        JobOpportunityEntity job = new JobOpportunityEntity("Company", "Dev", "Code", "Gbg", "Full-time");

        // Mockar repository så att findById returnerar ett Optional med vårt jobb
        when(repository.findById(1L)).thenReturn(Optional.of(job));

        // Anrop till service-metoden
        Optional<JobOpportunityEntity> result = service.getJobOpportunityById(1L);

        // Verifiera att det returnerade resultatet inte är tomt och innehåller vårt jobb
        assertThat(result).isPresent().contains(job);
    }


    @Test
    void shouldSaveNewJobOpportunity() {
        JobOpportunityEntity job = new JobOpportunityEntity("Company", "Dev", "Code", "Gbg", "Full-time");

        when(repository.save(job)).thenReturn(job);

        JobOpportunityEntity result = service.createJobOpportunity(job);

        assertThat(result).isEqualTo(job);
    }

    @Test
    void shouldDeleteJobOpportunity() {
        service.deleteJobOpportunity(42L);
        verify(repository).deleteById(42L);
    }

    @Test
    void shouldThrowExceptionWhenMissingCompanyName() {
        assertThatThrownBy(() -> service.registerNewJob("", "Title", "Desc", "Loc", "Terms"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name");
    }

    @Test
    void shouldThrowExceptionWhenMissingJobTitle() {
        assertThatThrownBy(() -> service.registerNewJob("Company", "", "Desc", "Loc", "Terms"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Jobtitle");
    }

    @Test
    void shouldThrowExceptionWhenMissingJobDescription() {
        assertThatThrownBy(() -> service.registerNewJob("Company", "Title", "", "Loc", "Terms"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Job description");
    }

    @Test
    void shouldThrowExceptionWhenMissingLocation() {
        assertThatThrownBy(() -> service.registerNewJob("Company", "Title", "Desc", "", "Terms"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Location");
    }

    @Test
    void shouldThrowExceptionWhenMissingTermsOfEmployment() {
        assertThatThrownBy(() -> service.registerNewJob("Company", "Title", "Desc", "Loc", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Terms Of Employment");
    }
}