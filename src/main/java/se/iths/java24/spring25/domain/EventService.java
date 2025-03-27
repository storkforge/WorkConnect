package se.iths.java24.spring25.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java24.spring25.dto.EventDTO;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventRepository eventRepository;

    public EventDTO createEvent(EventDTO eventDto) {
        EventEntity event = eventRepository.save(eventMapper.map(eventDto));
        return eventMapper.map(event);
    }

    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id).map(eventMapper::map);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::map).toList();
    }

    public EventDTO updateEvent(EventDTO eventDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteEventById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}