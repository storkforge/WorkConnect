package se.iths.java24.spring25.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.dto.InternshipDTO;
import se.iths.java24.spring25.entity.InternshipEntity;
import se.iths.java24.spring25.repository.InternshipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipService {

    @Autowired
    private InternshipMapper internshipMapper;

    @Autowired
    private InternshipRepository internshipRepository;

    public InternshipDTO createInternship(InternshipDTO internshipDto) {
        InternshipEntity internship = internshipRepository.save(internshipMapper.map(internshipDto));
        return internshipMapper.map(internship);
    }

    public Optional<InternshipDTO> getInternshipById(Long id) {
        return internshipRepository.findById(id).map(internshipMapper::map);
    }

    public List<InternshipDTO> getAllInternship() {
        return internshipRepository.findAll().stream().map(internshipMapper::map).toList();
    }

    public InternshipDTO updateInternship(InternshipDTO internshipDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteInternshipById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}