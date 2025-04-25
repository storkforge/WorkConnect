package se.iths.java24.spring25.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This will look for home.html in templates folder
    }


}

