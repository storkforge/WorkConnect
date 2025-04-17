package se.iths.java24.spring25.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
class EventController {

    private final EventMapper eventMapper;

    private final EventService eventService;

    EventController(EventMapper eventMapper, EventService eventService) {
        this.eventMapper = eventMapper;
        this.eventService = eventService;
    }

    // Endpoint to create a new work event
    //@PreAuthorize("hasAuthority('CREATE_EVENT_AUTHORITY')") // All @PreAuthorize needs to be added to the correct roles
    @PostMapping
    ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDto) {
        EventEntity savedEvent  = eventService.createEvent(eventMapper.map(eventDto));
        return new ResponseEntity<>(eventMapper.map(savedEvent), HttpStatus.CREATED);
    }

    // Endpoint to update a work event
    //@PreAuthorize("hasAuthority('UPDATE_EVENT_AUTHORITY')")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDto) {
        EventEntity eventEntity = eventMapper.map(eventDto);
        eventEntity.setId(id);
        eventService.updateEvent(eventEntity);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all events
    //@PreAuthorize("hasAuthority('READ_EVENT_AUTHORITY')")
    @GetMapping
    ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents()
                .stream()
                .map(this.eventMapper::map)
                .toList();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Endpoint to get an event by ID
    //@PreAuthorize("hasAuthority('READ_EVENT_AUTHORITY')")
    @GetMapping("/{id}")
    ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(eventMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Endpoint to delete an event by ID
    //@PreAuthorize("hasAuthority('DELETE_EVENT_AUTHORITY')")
    @DeleteMapping("/{id}")
    ResponseEntity <Void> deleteEventById(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
