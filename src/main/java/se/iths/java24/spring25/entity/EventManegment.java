package se.iths.java24.spring25.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class EventManegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;

    @OneToMany(mappedBy = "event")
    private List<Registration> registrations;

    @OneToMany(mappedBy = "event")
    private List<JobOpportunity> opportunities;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<JobOpportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<JobOpportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
