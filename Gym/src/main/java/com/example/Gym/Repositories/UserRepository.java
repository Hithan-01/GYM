package com.example.Gym.Repositories;

import com.example.Gym.Entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // Custom query to find a user by username

    boolean existsByUsername(String username);  // Custom query to check if a user exists by username
}
