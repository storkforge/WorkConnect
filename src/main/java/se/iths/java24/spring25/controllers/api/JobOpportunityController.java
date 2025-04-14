package se.iths.java24.spring25.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.service.JobOpportunityService;

import java.util.List;

@RestController
//@RequestMapping("/api/jobOppertunities")
@RequestMapping("/jobs/new")
public class JobOpportunityController {

    private final JobOpportunityService jobOpportunityService;
    private final JobOpportunityMapper jobOpportunityMapper;

    JobOpportunityController(JobOpportunityService jobOpportunityService, JobOpportunityMapper jobOpportunityMapper) {
        this.jobOpportunityService = jobOpportunityService;
        this.jobOpportunityMapper = jobOpportunityMapper;
    }

    // Endpoint to create a new job Opportunity
    @PreAuthorize("hasAuthority('CREATE_JOBOPPORTUNITY_AUTHORITY')") // All @PreAuthorize needs to be added to the correct roles
    @PostMapping
    public ResponseEntity<JobOpportunityDTO> createJobOpportunity(@RequestBody JobOpportunityDTO jobOpportunityDto) {
        JobOpportunityEntity savedJobOpportunity = jobOpportunityService.createJobOpportunity(jobOpportunityMapper
                .map(jobOpportunityDto));
        return new ResponseEntity<>(jobOpportunityMapper.map(savedJobOpportunity), HttpStatus.CREATED);
    }

    // Endpoint to update a job Opportunity
    @PreAuthorize("hasAuthority('UPDATE_JOBOPPORTUNITY_AUTHORITY')")
    @PatchMapping
    public ResponseEntity<Void> updateJobOpportunity(JobOpportunityDTO jobOpportunityDTO) {
        jobOpportunityService.updateJobOpportunity(jobOpportunityMapper
                .map(jobOpportunityDTO));
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all job Opportunity
    @PreAuthorize("hasAuthority('READ_JOBOPPORTUNITY_AUTHORITY')")
    @GetMapping
    public ResponseEntity<List<JobOpportunityDTO>> getAllJobOpportunities() {
        List<JobOpportunityDTO> jobOpportunities = jobOpportunityService.getAllJobOpportunities()
                .stream()
                .map(this.jobOpportunityMapper::map)
                .toList();
        return new ResponseEntity<>(jobOpportunities, HttpStatus.OK);
    }

    // Endpoint to get a job Opportunity by ID
    @PreAuthorize("hasAuthority('READ_JOBOPPORTUNITY_AUTHORITY')")
    @GetMapping("/{id}")
    public ResponseEntity<JobOpportunityDTO> getJobOpportunityById(@PathVariable Long id) {
        return jobOpportunityService.getJobOpportunityById(id)
                .map(jobOpportunityMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Endpoint to delete a job opportunity by ID
    @PreAuthorize("hasAuthority('DELETE_JOBOPPORTUNITY_AUTHORITY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOpportunityById(@PathVariable Long id) {
        jobOpportunityService.deleteJobOpportunity(id);
        return ResponseEntity.noContent().build();
    }
}
