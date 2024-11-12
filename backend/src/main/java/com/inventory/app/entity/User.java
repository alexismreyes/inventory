package com.inventory.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) // Adds a foreign key in the User table referencing Role
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Indicates this side will be serialized to dismiss infinite recursion
    private List<Order> orders = new ArrayList<>(); // Adds a relationship to retrieve orders for the user


}
