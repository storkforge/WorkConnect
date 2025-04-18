package se.iths.java24.spring25.service;

import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.repository.InternshipRepository;

import java.util.List;
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

    public void deleteInternship(Long id) {
        internshipRepository.deleteById(id);
    }

    public InternshipEntity updateInternship(InternshipEntity internship) {
        return internshipRepository.save(internship);
    }
}
