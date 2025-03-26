package se.iths.java24.spring25.repository;

import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Implement custom queries if needed
}
