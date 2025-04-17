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
import se.iths.java24.spring25.entity.UserEntity;
import se.iths.java24.spring25.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {

    // Implement user endpoints
    private final UserMapper userMapper;

    private final UserService userService;

    UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }


    // Endpoint to create a new user
    //@PreAuthorize("hasAuthority('CREATE_USER_AUTHORITY')") // All @PreAuthorize needs to be added to the correct roles
    @PostMapping
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        UserEntity savedUser = userService.createUser(userMapper.map(userDto));
        return new ResponseEntity<>(userMapper.map(savedUser), HttpStatus.CREATED);
    }

    // Endpoint to update a user
    //@PreAuthorize("hasAuthority('UPDATE_USER_AUTHORITY')")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDto) {
        UserEntity userEntity = userMapper.map(userDto);
        userEntity.setId(id);
        userService.updateUser(userEntity);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all users
    //@PreAuthorize("hasAuthority('READ_USER_AUTHORITY')")
    @GetMapping
    ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(this.userMapper::map)
                .toList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint to get a user by ID
    //@PreAuthorize("hasAuthority('READ_USER_AUTHORITY')")
    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Endpoint to delete a user by ID
    //@PreAuthorize("hasAuthority('DELETE_USER_AUTHORITY')")
    @DeleteMapping("/{id}")
    ResponseEntity <UserDTO> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}