package com.inventory.app.repository;

import com.inventory.app.entity.User;
import com.inventory.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); // Find user by username
    boolean existsByUsername(String username); // Check if username exists

    //Optional<Role> findRoleByName(String roleName); // Find a role by name

}
