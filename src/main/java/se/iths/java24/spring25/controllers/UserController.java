package se.iths.java24.spring25.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users") // Prefix for all endpoints
public class UserController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint, No authorisations needed!";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is a private endpoint. Only authenticated users can see this.";
    }
}
