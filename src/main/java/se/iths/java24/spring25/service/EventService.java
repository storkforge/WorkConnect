package se.iths.java24.spring25.service;

import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<EventEntity> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public EventEntity createEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public EventEntity updateEvent(EventEntity event) {
        return eventRepository.save(event);
    }
}
