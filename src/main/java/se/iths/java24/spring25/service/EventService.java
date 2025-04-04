package se.iths.java24.spring25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.EventManagement;
import se.iths.java24.spring25.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventManagement> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<EventManagement> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public EventManagement createEvent(EventManagement event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
