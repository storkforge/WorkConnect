package se.iths.java24.spring25.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // This isn't being used due to Spring boot security own login
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; // This will look for login.html in templates folder
//    }

    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This will look for home.html in templates folder
    }


}

