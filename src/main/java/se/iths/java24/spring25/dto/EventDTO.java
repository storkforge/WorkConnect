package se.iths.java24.spring25.dto;

import java.time.LocalDate;

public class EventDTO {
    private long id;
    private String name;
    private LocalDate date;

    // --- Getters & Setters ---
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}

// Implement event DTO fields and methods
