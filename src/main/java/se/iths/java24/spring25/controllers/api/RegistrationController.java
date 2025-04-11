package se.iths.java24.spring25.controllers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.iths.java24.spring25.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // thymeleaf template: register.html
    }


    @PostMapping("/register")
    public String handleRegistration(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        userService.registerUser(name, email, password);
        return "redirect:/login?registered";
    }
}
