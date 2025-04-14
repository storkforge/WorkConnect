package se.iths.java24.spring25.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.java24.spring25.service.JobOpportunityService;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

import java.util.List;

@Controller
public class JobWebController {

    private final JobOpportunityService jobService;

    public JobWebController(JobOpportunityService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public String showRecommendedJobs(Model model) {
        List<JobOpportunityEntity> jobs = jobService.getAllJobOpportunities(); // Replace with filtered list if needed
        model.addAttribute("jobs", jobs);
        return "jobs"; // jobs.html in templates folder
    }
}