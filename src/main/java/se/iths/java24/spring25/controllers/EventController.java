package se.iths.java24.spring25.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.iths.java24.spring25.entity.EventManegment;
import se.iths.java24.spring25.service.EventService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService EventService;

    @GetMapping
    public List<EventManegment> getAllEvents() {
        return EventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<EventManegment> getEventById(@PathVariable Long id) {
        return EventService.getEventById(id);
    }

    @PostMapping
    public EventManegment createEvent(@RequestBody EventManegment event) {
        return EventService.createEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        EventService.deleteEvent(id);
    }
}
