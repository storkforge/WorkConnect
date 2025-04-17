package se.iths.java24.spring25.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

//    @Autowired
//    public JobWebController(JobOpportunityService jobService) {
//        this.jobService = jobService;
//    }

//    @GetMapping("/jobs")
//    public String showRecommendedJobs(Model model) {
//        List<JobOpportunityEntity> jobs = jobService.getAllJobOpportunities(); // Replace with filtered list if needed
//        model.addAttribute("jobs", jobs);
//        return "jobs"; // jobs.html in templates folder
//    }

    @GetMapping("/jobs")
    public String showJobs(Model model) {
        List<JobOpportunityEntity> jobs = jobOpportunityRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs"; // This will map to a Thymeleaf template named jobs.html
    }

//    @Autowired
//    private JobOpportunityRepository jobOpportunityRepository;


//    public JobWebController(JobOpportunityService jobService, JobOpportunityRepository jobOpportunityRepository) {
//        this.jobService = jobService;
//        this.jobOpportunityRepository = jobOpportunityRepository;
//    }


//    @GetMapping("/jobs/jobregistration")
//    public String RegisterNewJob(Model model) {
//        List<JobOpportunityEntity> jobs = jobService.getAllJobOpportunities(); // Replace with filtered list if needed
//        model.addAttribute("jobs", jobs);
//        return "jobRegister"; // jobs.html in templates folder
//    }

//    @PostMapping("/jobs/jobregistration")
//    public String createJob(@ModelAttribute JobOpportunityEntity jobOpportunity) {
//        jobService.createJobOpportunity(jobOpportunity);
//        return "redirect:/jobs";
//    }

    @GetMapping("/jobs/jobregistration")
    public String showJobRegistrationForm(Model model) {
        model.addAttribute("jobOpportunity", new JobOpportunityEntity());
        return "jobRegister"; // jobs/jobregistration.html in templates folder
    }

    @PostMapping("/jobs/jobregistration")
    public String submitForm(@ModelAttribute JobOpportunityEntity jobOpportunity) {
        jobOpportunityRepository.save(jobOpportunity);
        return "redirect:/jobs/jobSuccess"; // see vist VALE!!!
    }

    // Optional: show success page
    @GetMapping("/jobs/jobSuccess")
    public String showSuccess() {
        return "jobSuccess";
    }

}