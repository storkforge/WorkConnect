package se.iths.java24.spring25.service;

import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.repository.InternshipRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;

    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    public List<InternshipEntity> getAllInternships() {
        return internshipRepository.findAll();
    }

    public Optional<InternshipEntity> getInternshipById(Long id) {
        return internshipRepository.findById(id);
    }

    public InternshipEntity createInternship(InternshipEntity internship) {
        return internshipRepository.save(internship);
    }

    public InternshipEntity updateInternship(Long id, InternshipEntity updatedInternship) {
        InternshipEntity existing = internshipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Internship with ID " + id + " not found"));

        existing.setTitle(updatedInternship.getTitle());
        existing.setCompany(updatedInternship.getCompany());
        existing.setDescription(updatedInternship.getDescription());
        existing.setLocation(updatedInternship.getLocation());
        existing.setStartDate(updatedInternship.getStartDate());
        existing.setEndDate(updatedInternship.getEndDate());
        existing.setEvent(updatedInternship.getEvent());
        existing.setStatus(updatedInternship.getStatus());

        return internshipRepository.save(existing);
    }

    public void deleteInternship(Long id) {
        if (!internshipRepository.existsById(id)) {
            throw new NoSuchElementException("Internship with ID " + id + " not found");
        }
        internshipRepository.deleteById(id);
    }

    public InternshipEntity updateInternship(InternshipEntity internship) {
        return internshipRepository.save(internship);
    }
}
