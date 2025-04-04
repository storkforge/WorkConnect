package se.iths.java24.spring25.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard"; // ⬅️ Thymeleaf-templaten dashboard.html
    }

    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "You have been logged out successfully.");
        return "logout"; // ⬅️ Thymeleaf-templaten logout.html
    }
}
