package se.iths.java24.spring25.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_opportunity_entity")
public class JobOpportunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description; // from V3

    @Column(name = "company_name")
    private String companyName; // from V9

    @Column(name = "job_title", unique = true)
    private String jobTitle;// from V9 unique

    @Column(name = "job_description")
    private String jobDescription; // from V9

    @Column(name = "location")
    private String location; // from V9

    @Column(name = "terms_of_employment")
    private String termsOfEmployment; // from V9


    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    public JobOpportunityEntity() {}

    public JobOpportunityEntity(String companyName, String jobTitle, String jobDescription, String location, String termsOfEmployment) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.location = location;
        this.termsOfEmployment = termsOfEmployment;
    }


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTermsOfEmployment() {
        return termsOfEmployment;
    }

    public void setTermsOfEmployment(String termsOfEmployment) {
        this.termsOfEmployment = termsOfEmployment;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public void setProvider(AuthProvider authProvider) {
    }

    public void setProviderId(Object o) {
    }

    @Override
    public String toString() {
        return "JobOpportunityEntity{" +
                "id=" + id +
                "companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", location='" + location + '\'' +
                ", termsOfEmployment='" + termsOfEmployment + '\'' +
                '}';
    }
}
