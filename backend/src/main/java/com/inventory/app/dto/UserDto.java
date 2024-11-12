package com.inventory.app.dto;

import java.util.List;

public class UserDto {
    private String username;
    private String password;
    private String role; // Single role name, e.g., "ROLE_USER"
    private List<OrderDto> orders;  // Optional field for orders

    // Constructors, Getters, and Setters

    public UserDto(String username, String password, String role, List<OrderDto> orders) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.orders = orders;
    }

    // Overloaded constructor without orders for simplicity when orders aren't needed
    public UserDto(String username, String password, String role) {
        this(username, password, role, null);
    }

    public UserDto() {
        // No-argument constructor for DTO instantiation
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
