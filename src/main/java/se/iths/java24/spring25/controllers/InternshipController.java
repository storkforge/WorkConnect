package se.iths.java24.spring25.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.iths.java24.spring25.domain.InternshipService;
import se.iths.java24.spring25.dto.InternshipDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    // Implement internship endpoints

@Autowired
private InternshipService internshipService;

// Endpoint to create a new internship
@PostMapping
public ResponseEntity<InternshipDTO> createInternship(@RequestBody InternshipDTO internshipDTO) {
    InternshipDTO savedInternship = internshipService.createInternship(internshipDTO);
    return new ResponseEntity<>(savedInternship, HttpStatus.CREATED);
}

// Endpoint to update a new internship
@PatchMapping
public ResponseEntity<Void> updateInternship(InternshipDTO internshipDto) {
    InternshipDTO savedInternship = internshipService.updateInternship(internshipDto);
    return ResponseEntity.noContent().build();
}

// Endpoint to get all internships
@GetMapping
public ResponseEntity<List<InternshipDTO>> getAllInternships() {
    List<InternshipDTO> internships = internshipService.getAllInternship();
    return new ResponseEntity<>(internships, HttpStatus.OK);
}

// Endpoint to get a internship by ID
@GetMapping("/{id}")
public ResponseEntity<InternshipDTO> getInternshipById(@PathVariable Long id) {
    Optional<InternshipDTO> Internship = internshipService.getInternshipById(id);
    return Internship.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
@DeleteMapping("/id")
public ResponseEntity <InternshipDTO> deleteInternshipById(@PathVariable Long id) {
    internshipService.deleteInternshipById(id);
    return ResponseEntity.noContent().build();
}
}
