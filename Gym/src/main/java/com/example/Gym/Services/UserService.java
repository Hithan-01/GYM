package com.example.Gym.Services;

import com.example.Gym.Entidades.Roles;
import com.example.Gym.Entidades.User;
import com.example.Gym.Repositories.UserRepository;
import com.example.Gym.Repositories.RolesRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Use the injected PasswordEncoder

   public User registerUser(String username, String password, String email, String roleName) {
    try {
        System.out.println("DEBUG: Fetching role from database: " + roleName);
        
        List<Roles> availableRoles = rolesRepository.findAll();
        System.out.println("DEBUG: Available roles in DB: " + availableRoles);

        Roles role = rolesRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        System.out.println("DEBUG: Role found: " + role.getName());

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);

    } catch (Exception e) {
        System.out.println("ERROR: Failed to register user - " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
}
public List<User> getAllUsers() {
    return userRepository.findAll(); // ✅ Fetch all users
}

}
