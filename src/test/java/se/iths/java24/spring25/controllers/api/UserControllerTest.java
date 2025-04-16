package se.iths.java24.spring25.controllers.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser() {
        // Arrange
        UserDTO inputDto = new UserDTO(null, "Alice", "alice@example.com", "secret123");
        UserEntity userEntity = new UserEntity();
        UserEntity savedEntity = new UserEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Alice");
        savedEntity.setEmail("alice@example.com");
        savedEntity.setPassword("secret123");

        UserDTO savedDto = new UserDTO(1L, "Alice", "alice@example.com", "secret123");

        when(userMapper.map(inputDto)).thenReturn(userEntity);
        when(userService.createUser(userEntity)).thenReturn(savedEntity);
        when(userMapper.map(savedEntity)).thenReturn(savedDto);

        // Act
        ResponseEntity<UserDTO> response = userController.createUser(inputDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedDto, response.getBody());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long id = 1L;
        UserDTO dto = new UserDTO(null, "Updated User", "updated@example.com", "newpass");
        UserEntity entity = new UserEntity();

        when(userMapper.map(dto)).thenReturn(entity);

        // Act
        ResponseEntity<Void> response = userController.updateUser(id, dto);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).updateUser(entity);
        assertEquals(id, entity.getId());
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("Bob");
        entity.setEmail("bob@example.com");
        entity.setPassword("bobpass");

        UserDTO dto = new UserDTO(1L, "Bob", "bob@example.com", "bobpass");

        when(userService.getAllUsers()).thenReturn(List.of(entity));
        when(userMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(dto, response.getBody().get(0));
    }

    @Test
    void testGetUserById_Found() {
        // Arrange
        Long id = 1L;
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setName("Charlie");
        entity.setEmail("charlie@example.com");
        entity.setPassword("charliepass");

        UserDTO dto = new UserDTO(id, "Charlie", "charlie@example.com", "charliepass");

        when(userService.getUserById(id)).thenReturn(Optional.of(entity));
        when(userMapper.map(entity)).thenReturn(dto);

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        Long id = 42L;
        when(userService.getUserById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteUserById() {
        // Arrange
        Long id = 3L;

        // Act
        ResponseEntity<UserDTO> response = userController.deleteUserById(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(id);
    }
}