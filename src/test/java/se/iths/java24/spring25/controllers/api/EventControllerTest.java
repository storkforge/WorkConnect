package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.java24.spring25.entity.EventEntity;
import se.iths.java24.spring25.service.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private EventMapper eventMapper;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @Test
    void testCreateEvent() {
        EventDTO inputDto = new EventDTO(null, "Team Meeting", LocalDate.of(2025, 4, 20));

        EventEntity entity = new EventEntity(); // Simulated entity before saving
        EventEntity savedEntity = new EventEntity(); // Entity after save, assume ID is set
        savedEntity.setId(1L);
        savedEntity.setName("Team Meeting");
        savedEntity.setDate(LocalDate.of(2025, 4, 20));

        EventDTO savedDto = new EventDTO(1L, "Team Meeting", LocalDate.of(2025, 4, 20));

        when(eventMapper.map(inputDto)).thenReturn(entity);
        when(eventService.createEvent(entity)).thenReturn(savedEntity);
        when(eventMapper.map(savedEntity)).thenReturn(savedDto);

        ResponseEntity<EventDTO> response = eventController.createEvent(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
    }

    @Test
    void testUpdateEvent() {
        // Arrange
        Long eventId = 1L;
        LocalDate date = LocalDate.of(2025, 4, 20);
        EventDTO dto = new EventDTO(null, "Updated Event", date); // ✅ proper record instantiation

        EventEntity entity = new EventEntity();
        entity.setId(eventId); // Set ID later, as controller does

        when(eventMapper.map(dto)).thenReturn(entity);

        // Act
        ResponseEntity<Void> response = eventController.updateEvent(eventId, dto);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventService).updateEvent(entity);
        assertEquals(eventId, entity.getId());
    }

    @Test
    void testGetAllEvents() {
        // Arrange
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(1L);
        eventEntity.setName("Conference");
        eventEntity.setDate(LocalDate.of(2025, 4, 22));

        List<EventEntity> entities = List.of(eventEntity);

        EventDTO dto = new EventDTO(1L, "Conference", LocalDate.of(2025, 4, 22)); // ✅ valid record instance

        when(eventService.getAllEvents()).thenReturn(entities);
        when(eventMapper.map(any(EventEntity.class))).thenReturn(dto);

        // Act
        ResponseEntity<List<EventDTO>> response = eventController.getAllEvents();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void testGetEventById_Found() {
        // Arrange
        Long id = 1L;
        EventEntity entity = new EventEntity();
        entity.setId(id);
        entity.setName("Sprint Review");
        entity.setDate(LocalDate.of(2025, 4, 25));

        EventDTO dto = new EventDTO(id, "Sprint Review", LocalDate.of(2025, 4, 25)); // ✅ valid record constructor

        when(eventService.getEventById(id)).thenReturn(Optional.of(entity));
        when(eventMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<EventDTO> response = eventController.getEventById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetEventById_NotFound() {
        Long id = 1L;
        when(eventService.getEventById(id)).thenReturn(Optional.empty());

        ResponseEntity<EventDTO> response = eventController.getEventById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteEventById() {
        Long id = 1L;

        ResponseEntity<Void> response = eventController.deleteEventById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventService).deleteEvent(id);
    }
}
