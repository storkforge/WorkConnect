package se.iths.java24.spring25.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.java24.spring25.dto.JobOpportunityDTO;
import se.iths.java24.spring25.domain.JobOpportunityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobOppertunities")
public class JobOpportunityController {

    // Implement job opportunity endpoints

    @Autowired
    private JobOpportunityService jobOpportunityService;

    // Endpoint to create a new job Opportunity
    @PostMapping
    public ResponseEntity<JobOpportunityDTO> createJobOpportunity(@RequestBody JobOpportunityDTO jobbOpportunityDto) {
        JobOpportunityDTO savedJobOpportunity = jobOpportunityService.createJobOpportunity(jobbOpportunityDto);
        return new ResponseEntity<>(savedJobOpportunity, HttpStatus.CREATED);
    }

    // Endpoint to update a new job Opportunity
    @PatchMapping
    public ResponseEntity<Void> updateJobOpportunity(JobOpportunityDTO jobOpportunityDTO) {
        JobOpportunityDTO savedJobOpportunity = jobOpportunityService.updateJobOpportunity(jobOpportunityDTO);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all job Opportunity
    @GetMapping
    public ResponseEntity<List<JobOpportunityDTO>> getAllJobOpportunities() {
        List<JobOpportunityDTO> jobOpportunity = jobOpportunityService.getAllJobOpportunities();
        return new ResponseEntity<>(jobOpportunity, HttpStatus.OK);
    }

    // Endpoint to get a job Opportunity by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobOpportunityDTO> getJobOpportunity(@PathVariable Long id) {
        Optional<JobOpportunityDTO> JobOpportunity = jobOpportunityService.getJobOpportunityById(id);
        return JobOpportunity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @DeleteMapping("/id")
    public ResponseEntity <JobOpportunityDTO> deleteJobOpportunityById(@PathVariable Long id) {
        jobOpportunityService.deleteJobOpportunityById(id);
        return ResponseEntity.noContent().build();
    }
}
