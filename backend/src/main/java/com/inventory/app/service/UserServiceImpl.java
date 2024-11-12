package com.inventory.app.service;
import com.inventory.app.dto.UserDto;
import com.inventory.app.entity.User;
import com.inventory.app.entity.Role;
import com.inventory.app.repository.RoleRepository;
import com.inventory.app.repository.UserRepository;
import com.inventory.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final OrderService orderService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.orderService = orderService;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName)  // Use the RoleRepository to find by role name
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }


    // New methods for CRUD functionality

    @Override
    public User createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public User updateUser(Long id, UserDto updatedUserDto) {
        User user = getUserById(id);
        user.setUsername(updatedUserDto.getUsername());

        // Re-encode password only if it was changed
        if (!user.getPassword().equals(updatedUserDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        }

        // Find and set role if needed
        if (updatedUserDto.getRole() != null) {
            Role role = findRoleByName(updatedUserDto.getRole());
            user.setRole(role);
        }

        // Update other fields as needed
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().getName()); // Assuming Role entity has a `getName` method
        userDto.setOrders(user.getOrders().stream()
                .map(orderService::convertToDto)
                .collect(Collectors.toList()));
        return userDto;
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());

        // Encode password and find role by name
        user.setPassword(userDto.getPassword());
        if (userDto.getRole() != null) {
            user.setRole(findRoleByName(userDto.getRole()));
        }
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Get the role name from the user's role
        String roleName = user.getRole().getName();  // Assuming Role entity has a `getName` method

        // Create a SimpleGrantedAuthority from the role name
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        // Return a UserDetails object, passing in the username, password, and authority
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authority) // Pass authority
                .build();
    }

}
