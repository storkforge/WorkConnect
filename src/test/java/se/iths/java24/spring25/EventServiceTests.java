package se.iths.java24.spring25;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.repository.EventRepository;
import se.iths.java24.spring25.service.EventService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class EventServiceTests {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    public EventServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEvents_shouldReturnListOfEvents() {
        EventEntity event = new EventEntity();
        event.setName("Test Event");
        event.setDate(LocalDate.now());

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        var result = eventService.getAllEvents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Event");
    }

    @Test
    void getEventById_shouldReturnEventById() {
        EventEntity event = new EventEntity();
        event.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        var result = eventService.getEventById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
    }

    @Test
    void createEvent_shouldSaveAndReturnEvent() {
        EventEntity event = new EventEntity();
        event.setName("Created Event");

        when(eventRepository.save(event)).thenReturn(event);

        var result = eventService.createEvent(event);

        assertThat(result.getName()).isEqualTo("Created Event");
    }

    @Test
    void deleteEvent_shouldCallDeleteById() {
        eventService.deleteEvent(1L);

        verify(eventRepository, times(1)).deleteById(1L);
    }

}
