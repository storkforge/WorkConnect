package se.iths.java24.spring25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.java24.spring25.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Implement custom queries if needed
}
