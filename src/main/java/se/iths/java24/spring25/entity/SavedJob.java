package se.iths.java24.spring25.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private JobOpportunityEntity job;

    private LocalDateTime savedAt = LocalDateTime.now();
}
