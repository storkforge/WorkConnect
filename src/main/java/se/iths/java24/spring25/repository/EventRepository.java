package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.EventManegment;

public interface EventRepository extends JpaRepository<EventManegment, Long> {
    // Implement custom queries if needed
}
