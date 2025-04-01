package se.iths.java24.spring25.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.java24.spring25.domain.UserService;
import se.iths.java24.spring25.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Implement user endpoints
    @Autowired
    private UserService userService;

    // Endpoint to create a new user
    @PreAuthorize("hasAuthority('CREATE_USER_AUTHORITY')") // All @PreAuthorize needs to be added to the correct rolles
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        UserDTO savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Endpoint to update a user
    @PreAuthorize("hasAuthority('UPDATE_USER_AUTHORITY')")
    @PatchMapping
    public ResponseEntity<Void> updateUser(UserDTO userDto) {
        UserDTO savedUser = userService.updateUser(userDto);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get all users
    @PreAuthorize("hasAuthority('READ_USER_AUTHORITY')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint to get a user by ID
    @PreAuthorize("hasAuthority('READ_USER_AUTHORITY')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> User = userService.getUserById(id);
        return User.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    // Endpoint to delete a user by ID
    @PreAuthorize("hasAuthority('DELETE_USER_AUTHORITY')")
    @DeleteMapping("/id")
    public ResponseEntity <UserDTO> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}