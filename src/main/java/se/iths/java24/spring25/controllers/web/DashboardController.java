package se.iths.java24.spring25.controllers.web;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

import java.security.Principal;

@Controller
public class DashboardController {

    private final UserRepository userRepository;

    public DashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        String email = principal.getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        model.addAttribute("user", user);
        return "dashboard";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "You have been logged out successfully.");
        return "logout"; // ⬅️ logout.html Thymeleaf-sida
    }

    @GetMapping("/saved-jobs")
    public String showSavedJobs() {
        return "saved-jobs";
    }

    @GetMapping("/applied-jobs")
    public String showAppliedJobs() {
        return "applied-jobs";
    }
}
