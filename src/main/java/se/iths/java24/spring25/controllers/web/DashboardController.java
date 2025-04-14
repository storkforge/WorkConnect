package se.iths.java24.spring25.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

/**
 * Controller responsible for dashboard-related views and logout functionality.
 */

    @Controller
    public class DashboardController {

        private final UserRepository userRepository;

        public DashboardController(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @GetMapping("/dashboard")
        public String showDashboard(Model model, Principal principal) {
            String email = principal.getName();
            Optional<UserEntity> user = userRepository.findByEmail(email);

            model.addAttribute("user", user);
            return "dashboard";
        }

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "You have been logged out successfully.");
        return "logout"; // ⬅️ Thymeleaf-templaten logout.html
    }
}
