package se.iths.java24.spring25.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.java24.spring25.repository.JobOpportunityRepository;
import se.iths.java24.spring25.service.JobOpportunityService;
import se.iths.java24.spring25.entity.JobOpportunityEntity;

import java.util.List;

@Controller
public class JobWebController {

    private final JobOpportunityService jobService;
    private JobOpportunityRepository jobOpportunityRepository;

    public JobWebController(JobOpportunityService jobService, JobOpportunityRepository jobOpportunityRepository) {
        this.jobService = jobService;
        this.jobOpportunityRepository = jobOpportunityRepository;
    }

    @GetMapping("/jobs")
    public String showJobs(Model model) {
        List<JobOpportunityEntity> jobs = jobOpportunityRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs"; // This will map to a Thymeleaf template named jobs.html
    }

    @GetMapping("/jobs/jobregistration")
    public String showJobRegistrationForm(Model model) {
        model.addAttribute("jobOpportunity", new JobOpportunityEntity());
        return "jobRegister"; // jobs/jobregistration.html in templates folder
    }

    @PostMapping("/jobs/jobregistration")
    public String submitForm(@ModelAttribute JobOpportunityEntity jobOpportunity, Model model) {
        try {
            jobService.registerNewJob(
                    jobOpportunity.getCompanyName(),
                    jobOpportunity.getJobTitle(),
                    jobOpportunity.getJobDescription(),
                    jobOpportunity.getLocation(),
                    jobOpportunity.getTermsOfEmployment()
            );
            return "redirect:/jobs/jobSuccess";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "jobRegister";
        }
    }

    @GetMapping("/jobs/jobSuccess")
    public String showSuccess() {
        return "jobSuccess";
    }
}