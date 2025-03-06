package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);  // Custom query to find a role by its name

}
