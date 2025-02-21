package com.pradip.CollaborativeTaskManagement.Repository;


import com.pradip.CollaborativeTaskManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // To find a user by username (for login/authentication).
    boolean existsByEmail(String email); // To check if an email is already registered.
    boolean existsByUsername(String username); // To check if a username is already taken.
}

