package se.iths.java24.spring25.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NetworkController {

    @GetMapping("/network")
    public String showNetworkPage(Model model) {
        // CONTROLLER IMPLEMENTATION NEEDED:
        // Later, you would fetch network data (connections, invitations)
        // and add it to the model.
        // model.addAttribute("connections", networkService.getConnections());
        // model.addAttribute("invitations", networkService.getInvitations());

        // Return the name of the Thymeleaf template to render
        return "network"; // This maps to network.html
    }
}
