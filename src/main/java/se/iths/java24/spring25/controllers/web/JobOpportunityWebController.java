package se.iths.java24.spring25.controllers.web;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import se.iths.java24.spring25.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class JobOpportunityWebController {
    private final JobOpportunityRepository jobRepo;
    private final SavedJobRepository savedJobRepo;
    private final JobApplicationRepository jobAppRepo;
    private final UserRepository userRepository;

    public JobOpportunityWebController(
            JobOpportunityRepository jobRepo,
            SavedJobRepository savedJobRepo,
            JobApplicationRepository jobAppRepo, UserRepository userRepository) {
        this.jobRepo = jobRepo;
        this.savedJobRepo = savedJobRepo;
        this.jobAppRepo = jobAppRepo;
        this.userRepository = userRepository;
    }

//    // Dummy user for now
//    private UserEntity getCurrentUser() {
//        UserEntity user = new UserEntity();
//        user.setId(1L);
//        user.setName("testuser");
//        return user;
//    }


    private UserEntity getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email;
        if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername(); // Spring stores username as email
        } else {
            email = principal.toString();
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @GetMapping("/jobs")
    public String showJobs(Model model) {
        UserEntity user = getCurrentUser();
        List<JobOpportunityEntity> jobs = jobRepo.findAll();

        List<SavedJob> savedJobs = savedJobRepo.findByUser(user);
        Set<Long> savedJobIds = savedJobs.stream()
                .map(savedJob -> savedJob.getJob().getId())
                .collect(Collectors.toSet());

        model.addAttribute("jobs", jobs);
        model.addAttribute("savedJobIds", savedJobIds);
        return "jobs";
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

        List<SavedJob> savedJobs = savedJobRepo.findByUser(user);
        List<JobOpportunityEntity> jobs = savedJobs.stream()
                .map(SavedJob::getJob)
                .toList();

        model.addAttribute("savedJobs", jobs);
        return "saved-jobs";
    }

    @GetMapping("/jobs/applied")
    public String showAppliedJobs(Model model) {
        UserEntity user = getCurrentUser(); // simulate or get from logged-in session

        List<JobApplication> appliedJobs = jobAppRepo.findByUser(user);
        List<JobOpportunityEntity> jobs = appliedJobs.stream()
                .map(JobApplication::getJob)
                .toList();

        model.addAttribute("appliedJobs", jobs);
        return "applied-jobs";
    }
}
