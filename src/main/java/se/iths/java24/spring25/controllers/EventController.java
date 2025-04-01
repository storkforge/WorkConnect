package se.iths.java24.spring25.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.java24.spring25.domain.EventService;
import se.iths.java24.spring25.dto.EventDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Endpoint to create a new work event
    @PreAuthorize("hasAuthority('CREATE_EVENT_AUTHORITY')") // All @PreAuthorize needs to be added to the correct rolles
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDto) {
        EventDTO savedEvent = eventService.createEvent(eventDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // Endpoint to update a new work event
    @PreAuthorize("hasAuthority('UPDATE_EVENT_AUTHORITY')")
    @PatchMapping
    public ResponseEntity<Void> updateEvent(EventDTO eventDto) {
        EventDTO savedEvent = eventService.updateEvent(eventDto);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all events
    @PreAuthorize("hasAuthority('READ_EVENT_AUTHORITY')")
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Endpoint to get an event by ID
    @PreAuthorize("hasAuthority('READ_EVENT_AUTHORITY')")
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Optional<EventDTO> Event = eventService.getEventById(id);
        return Event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PreAuthorize("hasAuthority('DELETE_EVENT_AUTHORITY')")
    @DeleteMapping("/id")
    public ResponseEntity <EventDTO> deleteEventById(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }
}
