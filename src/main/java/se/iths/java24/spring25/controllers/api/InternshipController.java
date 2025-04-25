package se.iths.java24.spring25.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.service.InternshipService;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
class InternshipController {

    // Implement internship endpoints
    private final InternshipMapper internshipMapper;
    private final InternshipService internshipService;

    InternshipController(InternshipMapper internshipMapper, InternshipService internshipService) {
        this.internshipMapper = internshipMapper;
        this.internshipService = internshipService;
    }

    // Endpoint to create a new internship
    //@PreAuthorize("hasAuthority('CREATE_INTERNSHIP_AUTHORITY')") // All @PreAuthorize needs to be added to correct roles
    @PostMapping
    ResponseEntity<InternshipDTO> createInternship(@RequestBody InternshipDTO internshipDTO) {
        InternshipEntity savedInternship = internshipService.createInternship(internshipMapper.map(internshipDTO));
        return new ResponseEntity<>(internshipMapper.map(savedInternship), HttpStatus.CREATED);
    }

    // Endpoint to update an internship
    //@PreAuthorize("hasAuthority('UPDATE_INTERNSHIP_AUTHORITY')")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateInternship(@PathVariable Long id, @RequestBody InternshipDTO internshipDto) {
        InternshipEntity internshipEntity = internshipMapper.map(internshipDto);
        internshipEntity.setId(id);
        internshipService.updateInternship(internshipEntity);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all internships
    //@PreAuthorize("hasAuthority('READ_INTERNSHIP_AUTHORITY')")
    @GetMapping
    ResponseEntity<List<InternshipDTO>> getAllInternships() {
        List<InternshipDTO> internships = internshipService.getAllInternships()
                .stream()
                .map(this.internshipMapper::map)
                .toList();
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    // Endpoint to get an internship by ID
    //@PreAuthorize("hasAuthority('READ_INTERNSHIP_AUTHORITY')")
    @GetMapping("/{id}")
    ResponseEntity<InternshipDTO> getInternshipById(@PathVariable Long id) {
        return internshipService.getInternshipById(id)
                .map(internshipMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Endpoint to delete an internship by ID
    //@PreAuthorize("hasAuthority('DELETE_INTERNSHIP_AUTHORITY')")
    @DeleteMapping("/{id}")
    ResponseEntity <Void> deleteInternshipById(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.noContent().build();
    }
}
