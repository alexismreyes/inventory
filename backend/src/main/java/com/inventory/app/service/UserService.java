package com.inventory.app.service;

import com.inventory.app.dto.UserDto;
import com.inventory.app.entity.User;
import com.inventory.app.entity.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void saveUser(User user); // Existing method for saving a user
    Optional<User> findByUsername(String username); // Existing method to find user by username
    boolean existsByUsername(String username); // Check if the username exists
    Role findRoleByName(String roleName); // Find a role by its name
    UserDetails loadUserByUsername(String username);

    // New CRUD operations
    User createUser(UserDto userDto); // Create a new user
    User getUserById(Long id); // Get user by ID
    User updateUser(Long id, UserDto updatedUserDto); // Update user details
    void deleteUser(Long id); // Delete a user
    List<User> getAllUsers(); // Get all users

    public UserDto convertToDto(User user);
    User convertToEntity(UserDto userDto); // New method to convert UserDto to User
}
