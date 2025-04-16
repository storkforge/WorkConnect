package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import se.iths.java24.spring25.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void testMapToDTO() {
        // Arrange
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("Jane Doe");
        entity.setEmail("jane@example.com");
        entity.setPassword("secure123");

        // Act
        UserDTO dto = userMapper.map(entity);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Jane Doe", dto.name());
        assertEquals("jane@example.com", dto.email());
        assertEquals("secure123", dto.password());
    }

    @Test
    void testMapToEntity() {
        // Arrange
        UserDTO dto = new UserDTO(null, "John Doe", "john@example.com", "pass123");

        // Act
        UserEntity entity = userMapper.map(dto);

        // Assert
        assertNull(entity.getId()); // ID is not mapped
        assertEquals("John Doe", entity.getName());
        assertEquals("john@example.com", entity.getEmail());
        assertEquals("pass123", entity.getPassword());
    }
}