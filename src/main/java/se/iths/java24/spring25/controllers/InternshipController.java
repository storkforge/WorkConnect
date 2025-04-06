package se.iths.java24.spring25.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.service.InternshipService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/internships")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @GetMapping
    public List<InternshipEntity> getAllInternships() {
        return internshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public Optional<InternshipEntity> getInternshipById(@PathVariable Long id) {
        return internshipService.getInternshipById(id);
    }

    @PostMapping
    public InternshipEntity createInternship(@RequestBody InternshipEntity internship) {
        return internshipService.createInternship(internship);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
    }
}
