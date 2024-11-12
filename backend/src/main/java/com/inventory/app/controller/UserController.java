package com.inventory.app.controller;

import com.inventory.app.dto.UserDto;
import com.inventory.app.entity.User;
import com.inventory.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);  // Convert DTO to entity
        UserDto savedUserDto = userService.convertToDto(user);  // Convert entity to DTO
        return ResponseEntity.ok(savedUserDto);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            UserDto userDto = userService.convertToDto(user); // Convert entity to DTO
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUserDto) {
        User updatedUser = userService.updateUser(id, updatedUserDto); // Convert DTO to entity
        if (updatedUser != null) {
            UserDto userDto = userService.convertToDto(updatedUser); // Convert entity to DTO
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(userService::convertToDto)
                .collect(Collectors.toList()); // Convert all entities to DTOs
        return ResponseEntity.ok(userDtos);
    }
}
