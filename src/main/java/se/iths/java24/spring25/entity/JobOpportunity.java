package se.iths.java24.spring25.entity;

import jakarta.persistence.*;



@Entity
public class JobOpportunity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventManagement event;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventManagement getEvent() {
        return event;
    }

    public void setEvent(EventManagement event) {
        this.event = event;
    }
}
