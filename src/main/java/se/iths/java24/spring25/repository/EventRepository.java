package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    // Implement custom queries if needed
}
