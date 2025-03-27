package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Implement custom queries if needed
}
