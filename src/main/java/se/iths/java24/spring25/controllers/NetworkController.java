package se.iths.java24.spring25.controllers; // Or your relevant controllers package

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// Import other necessary classes later (e.g., Services, DTOs)

@Controller
public class NetworkController {

    // Inject services later if needed for real data
    // Example: private final NetworkService networkService;

    // public NetworkController(NetworkService networkService) {
    //     this.networkService = networkService;
    // }

    @GetMapping("/network")
    public String showNetworkPage(Model model) {

        // --- Add Dummy Data to Model ---
        // Replace these with real data fetched from your services later

        // Dummy counts for the left sidebar card
        model.addAttribute("connectionCount", 138); // Example static value
        model.addAttribute("invitationCount", 2);   // Example static value
        model.addAttribute("eventCount", 5);      // Example static value
        model.addAttribute("groupCount", 3);      // Example static value
        model.addAttribute("pageCount", 12);      // Example static value

        // Dummy lists for the main content (will be used later with th:each)
        // For now, the HTML uses static examples, so these aren't strictly needed yet,
        // but we add them to show how it *would* be done.
        // model.addAttribute("connections", List.of()); // Replace with real data later
        // model.addAttribute("invitationsList", List.of()); // Replace with real data later
        // model.addAttribute("suggestedUsers", List.of()); // Replace with real data later

        // Return the name of the Thymeleaf template
        return "network"; // This maps to network.html
    }
}
