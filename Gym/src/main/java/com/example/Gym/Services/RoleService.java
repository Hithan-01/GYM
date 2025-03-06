package com.example.Gym.Services;

import com.example.Gym.Entidades.Roles;
import com.example.Gym.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RolesRepository roleRepository;

    public Optional<Roles> findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    

   public List<Roles> getAllRoles()
{
    return roleRepository.findAll();
}}
