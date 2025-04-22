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

}
