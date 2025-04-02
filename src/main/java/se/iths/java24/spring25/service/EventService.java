package se.iths.java24.spring25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.entity.EventManegment;
import se.iths.java24.spring25.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventManegment> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<EventManegment> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public EventManegment createEvent(EventManegment event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
