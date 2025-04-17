package se.iths.java24.spring25.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private JobOpportunityEntity job;

    private String status = "APPLIED";
    private LocalDateTime appliedAt = LocalDateTime.now();
}
