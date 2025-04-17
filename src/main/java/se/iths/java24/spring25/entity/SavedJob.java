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

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public JobOpportunityEntity getJob() {
        return job;
    }

    public void setJob(JobOpportunityEntity job) {
        this.job = job;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
