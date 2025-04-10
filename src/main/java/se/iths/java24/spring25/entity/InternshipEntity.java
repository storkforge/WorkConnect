package se.iths.java24.spring25.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "internships") // Tabellnamn för internships
public class InternshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String description;
    private String startDate;
    private String endDate;

    // Konstruktorer
    public InternshipEntity() {}

    public InternshipEntity(String title, String company, String location,
                            String description, String startDate, String endDate) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters och Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
