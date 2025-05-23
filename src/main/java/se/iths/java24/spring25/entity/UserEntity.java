package se.iths.java24.spring25.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public UserEntity() {}

    // Konstruktor utan ID och providerId
    public UserEntity(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.provider = AuthProvider.LOCAL;
        this.role = role;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public AuthProvider getProvider() { return provider; }

    public void setProvider(AuthProvider provider) { this.provider = provider; }

    public String getProviderId() { return providerId; }

    public void setProviderId(String providerId) { this.providerId = providerId; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", provider=" + provider +
                ", providerId='" + providerId + '\'' +
                ", role=" + role +
                '}';
    }

}
