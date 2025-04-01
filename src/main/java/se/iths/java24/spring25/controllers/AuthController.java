package se.iths.java24.spring25.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // This will look for login.html in templates folder
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index"; // This will look for index.html in templates folder
    }


}

