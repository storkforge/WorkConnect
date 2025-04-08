package se.iths.java24.spring25.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for dashboard-related views and logout functionality.
 */
@Controller
public class DashboardController {

    /**
          * Displays the dashboard page.
          *
          * @param model the model to add attributes to
          * @return the view name for the dashboard
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard"; // ⬅️ Thymeleaf-templaten dashboard.html
    }

    /**
          * Displays the logout success page.
          *
          * @param model the model to add attributes to
          * @return the view name for the logout page
     */
    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "You have been logged out successfully.");
        return "logout"; // ⬅️ Thymeleaf-templaten logout.html
    }
}
