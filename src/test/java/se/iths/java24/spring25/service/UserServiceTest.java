package se.iths.java24.spring25.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.java24.spring25.entity.AuthProvider;
import se.iths.java24.spring25.entity.Role;
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserShouldSaveNewUserWhenInputIsValid() {
        String name = "John Doe";
        String email = "john@example.com";
        String password = "securepassword";
        UserEntity userEntity = new UserEntity(name, email, "hashedPassword", Role.USER);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");


        userService.registerUser(name, email, password);
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userCaptor.capture());

        UserEntity savedUser = userCaptor.getValue();
        assertEquals(name, savedUser.getName());
        assertEquals(email, savedUser.getEmail());
        assertEquals("hashedPassword", savedUser.getPassword());
        assertEquals(Role.USER, savedUser.getRole());
        assertEquals(AuthProvider.LOCAL, savedUser.getProvider());
    }

    @Test
    void registerUserShouldThrowExceptionWhenEmailIsInvalid() {
        String name = "John Doe";
        String email = "invalid-email";
        String password = "securepassword";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.registerUser(name, email, password));

        assertEquals("Email is not valid", exception.getMessage());
    }

    @Test
    void registerUserShouldThrowExceptionWhenEmailAlreadyExists() {
        String name = "John Doe";
        String email = "john@example.com";
        String password = "securepassword";
        UserEntity existingUser = new UserEntity(name, email, "hashedPassword", Role.USER);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        Exception exception = assertThrows(RuntimeException.class, () -> userService.registerUser(name, email, password));

        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    void getAllUsersShouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(
                new UserEntity("User1", "user1@example.com", "password1", Role.USER),
                new UserEntity("User2", "user2@example.com", "password2", Role.USER)
        ));

        List<UserEntity> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void getUserByIdShouldReturnUserWhenUserExists() {
        Long userId = 1L;
        UserEntity user = new UserEntity("John Doe", "john@example.com", "hashedPassword", Role.USER);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<UserEntity> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUserByIdShouldReturnEmptyWhenUserDoesNotExist() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        Optional<UserEntity> result = userService.getUserById(userId);
        assertFalse(result.isPresent());
    }

    @Test
    void deleteUserShouldCallDeleteById() {
        Long userId = 1L;
        userService.deleteUser(userId);
        verify(userRepository).deleteById(userId);
    }
}
