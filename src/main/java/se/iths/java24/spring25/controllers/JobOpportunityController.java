package se.iths.java24.spring25.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.service.JobOpportunityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job-opportunities")
public class JobOpportunityController {

    @Autowired
    private JobOpportunityService jobOpportunityService;

    @GetMapping
    public List<JobOpportunityEntity> getAllJobOpportunities() {
        return jobOpportunityService.getAllJobOpportunities();
    }

    @GetMapping("/{id}")
    public Optional<JobOpportunityEntity> getJobOpportunityById(@PathVariable Long id) {
        return jobOpportunityService.getJobOpportunityById(id);
    }

    @PostMapping
    public JobOpportunityEntity createJobOpportunity(@RequestBody JobOpportunityEntity jobOpportunity) {
        return jobOpportunityService.createJobOpportunity(jobOpportunity);
    }

    @DeleteMapping("/{id}")
    public void deleteJobOpportunity(@PathVariable Long id) {
        jobOpportunityService.deleteJobOpportunity(id);
    }
}
