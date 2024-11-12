package com.inventory.app.controller;


import com.inventory.app.dto.LoginRequestDto;
import com.inventory.app.dto.UserDto;
import com.inventory.app.entity.Role;
import com.inventory.app.entity.User;
import com.inventory.app.service.UserService;
import com.inventory.app.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        // Log the incoming role name
        //System.out.println("Looking for role: " + userDto.getRole());

        // Retrieve the Role entity based on the role name in UserDto
        Role role = userService.findRoleByName(userDto.getRole());
        if (role == null) {
            return ResponseEntity.badRequest().body("Invalid role provided.");
        }
        user.setRole(role);

        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }




    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequest) {

        // Find the user by username
        Optional<User> optionalUser= userService.findByUsername(loginRequest.getUsername());


        // Check if user is found and password matches
        if (optionalUser.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
            System.out.println("Login failed for username: " + loginRequest.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        // Generate token if credentials are valid
        User user = optionalUser.get();
        String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok().body("Bearer " + token); // Return token with 'Bearer' prefix
    }


}
