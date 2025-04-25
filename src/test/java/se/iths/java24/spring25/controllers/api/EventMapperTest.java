package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import se.iths.java24.spring25.entity.EventEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventMapperTest {

    private final EventMapper eventMapper = new EventMapper();

    @Test
    void testMapToDTO() {
        // Arrange
        EventEntity entity = new EventEntity();
        entity.setId(1L);
        entity.setName("Team Meeting");
        entity.setDate(LocalDate.of(2025, 4, 20));

        // Act
        EventDTO dto = eventMapper.map(entity);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Team Meeting", dto.name());
        assertEquals(LocalDate.of(2025, 4, 20), dto.date());
    }

    @Test
    void testMapToEntity() {
        // Arrange
        EventDTO dto = new EventDTO(null, "Sprint Review", LocalDate.of(2025, 4, 25));

        // Act
        EventEntity entity = eventMapper.map(dto);

        // Assert
        assertNull(entity.getId()); // ID should not be mapped
        assertEquals("Sprint Review", entity.getName());
        assertEquals(LocalDate.of(2025, 4, 25), entity.getDate());
    }
}