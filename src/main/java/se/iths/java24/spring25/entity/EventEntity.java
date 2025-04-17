package se.iths.java24.spring25.entity;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;

    @OneToMany(mappedBy = "event")
    private List<RegistrationEntity> registrations;

    @OneToMany(mappedBy = "event")
    private List<JobOpportunityEntity> jobOpportunities;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<RegistrationEntity> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<RegistrationEntity> registrations) {
        this.registrations = registrations;
    }

    public List<JobOpportunityEntity> getJobOpportunities() {
        return jobOpportunities;
    }

    public void setJobOpportunities(List<JobOpportunityEntity> jobOpportunities) {
        this.jobOpportunities = jobOpportunities;
    }
}
