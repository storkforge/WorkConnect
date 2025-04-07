package se.iths.java24.spring25.entity;

import jakarta.persistence.*;


@Entity
public class RegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     +     * The user associated with this registration.
     +     * Many registrations can belong to one user.
     +     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    /**
     +     * The event associated with this registration.
     +     * Many registrations can belong to one event.
     +     */
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
