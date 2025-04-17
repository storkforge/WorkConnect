package se.iths.java24.spring25.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.service.JobOpportunityService;

import java.util.List;

@RestController
@RequestMapping("/api/jobOppertunities")

class JobOpportunityController {

    private final JobOpportunityService jobOpportunityService;
    private final JobOpportunityMapper jobOpportunityMapper;

    JobOpportunityController(JobOpportunityService jobOpportunityService, JobOpportunityMapper jobOpportunityMapper) {
        this.jobOpportunityService = jobOpportunityService;
        this.jobOpportunityMapper = jobOpportunityMapper;
    }

    // Endpoint to create a new job Opportunity
    //@PreAuthorize("hasAuthority('CREATE_JOBOPPORTUNITY_AUTHORITY')") // All @PreAuthorize needs to be added to the correct roles
    @PostMapping
    ResponseEntity<JobOpportunityDTO> createJobOpportunity(@RequestBody JobOpportunityDTO jobOpportunityDto) {
        JobOpportunityEntity savedJobOpportunity = jobOpportunityService.createJobOpportunity(jobOpportunityMapper
                .map(jobOpportunityDto));
        return new ResponseEntity<>(jobOpportunityMapper.map(savedJobOpportunity), HttpStatus.CREATED);
    }

    // Endpoint to update a job Opportunity
    //@PreAuthorize("hasAuthority('UPDATE_JOBOPPORTUNITY_AUTHORITY')")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateJobOpportunity(@PathVariable Long id, @RequestBody JobOpportunityDTO jobOpportunityDTO) {
        JobOpportunityEntity jobOpportunityEntity = jobOpportunityMapper.map(jobOpportunityDTO);
        jobOpportunityEntity.setId(id);
        jobOpportunityService.updateJobOpportunity(jobOpportunityEntity);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all job Opportunity
    //@PreAuthorize("hasAuthority('READ_JOBOPPORTUNITY_AUTHORITY')")
    @GetMapping
    ResponseEntity<List<JobOpportunityDTO>> getAllJobOpportunities() {
        List<JobOpportunityDTO> jobOpportunities = jobOpportunityService.getAllJobOpportunities()
                .stream()
                .map(this.jobOpportunityMapper::map)
                .toList();
        return new ResponseEntity<>(jobOpportunities, HttpStatus.OK);
    }

    // Endpoint to get a job Opportunity by ID
    //@PreAuthorize("hasAuthority('READ_JOBOPPORTUNITY_AUTHORITY')")
    @GetMapping("/{id}")
    ResponseEntity<JobOpportunityDTO> getJobOpportunityById(@PathVariable Long id) {
        return jobOpportunityService.getJobOpportunityById(id)
                .map(jobOpportunityMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Endpoint to delete a job opportunity by ID
    //@PreAuthorize("hasAuthority('DELETE_JOBOPPORTUNITY_AUTHORITY')")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJobOpportunityById(@PathVariable Long id) {
        jobOpportunityService.deleteJobOpportunity(id);
        return ResponseEntity.noContent().build();
    }
}
