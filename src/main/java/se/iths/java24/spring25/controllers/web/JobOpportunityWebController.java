package se.iths.java24.spring25.controllers.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.iths.java24.spring25.entity.JobApplication;
import se.iths.java24.spring25.entity.JobOpportunityEntity;
import se.iths.java24.spring25.entity.SavedJob;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.JobApplicationRepository;
import se.iths.java24.spring25.repository.JobOpportunityRepository;
import se.iths.java24.spring25.repository.SavedJobRepository;

import java.util.List;

@Controller
public class JobOpportunityWebController {
    private final JobOpportunityRepository jobRepo;
    private final SavedJobRepository savedJobRepo;
    private final JobApplicationRepository jobAppRepo;

    public JobOpportunityWebController(
            JobOpportunityRepository jobRepo,
            SavedJobRepository savedJobRepo,
            JobApplicationRepository jobAppRepo) {
        this.jobRepo = jobRepo;
        this.savedJobRepo = savedJobRepo;
        this.jobAppRepo = jobAppRepo;
    }

    // Dummy user for now
    private UserEntity getCurrentUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("testuser");
        return user;
    }

    @PostMapping("/jobs/save/{id}")
    public String saveJob(@PathVariable Long id) {
        UserEntity user = getCurrentUser();
        JobOpportunityEntity job = jobRepo.findById(id).orElseThrow();

        if (!savedJobRepo.existsByUserAndJob(user, job)) {
            SavedJob savedJob = new SavedJob();
            savedJob.setUser(user);
            savedJob.setJob(job);
            savedJobRepo.save(savedJob);
        }
        return "redirect:/jobs";
    }

    @PostMapping("/jobs/apply/{id}")
    public String applyJob(@PathVariable Long id) {
        UserEntity user = getCurrentUser();
        JobOpportunityEntity job = jobRepo.findById(id).orElseThrow();

        if (!jobAppRepo.existsByUserAndJob(user, job)) {
            JobApplication application = new JobApplication();
            application.setUser(user);
            application.setJob(job);
            jobAppRepo.save(application);
        }

        return "redirect:/jobs";
    }

    @GetMapping("/jobs/saved")
    public String showSavedJobs(Model model) {
        UserEntity user = getCurrentUser(); // simulate or get from logged-in session

//        List<SavedJob> savedJobs = savedJobRepo.findByUser(user);
        List<SavedJob> savedJobs = (List<SavedJob>) (List<?>) savedJobRepo.findByUser(user);


        // Extract just the job opportunity from each saved job
        List<JobOpportunityEntity> jobs = savedJobs.stream()
                .map(SavedJob::getJob)
                .toList();

        model.addAttribute("savedJobs", jobs);
        return "saved-jobs";
    }

    @GetMapping("/jobs/applied")
    public String showAppliedJobs(Model model) {
        UserEntity user = getCurrentUser(); // simulate or get from logged-in session

        List<JobApplication> appliedJobs = (List<JobApplication>) (List<?>) jobAppRepo.findByUser(user);


        // Extract just the job opportunity from each saved job
        List<JobOpportunityEntity> jobs = appliedJobs.stream()
                .map(JobApplication::getJob)
                .toList();

        model.addAttribute("appliedJobs", jobs);
        return "applied-jobs";
    }

}
