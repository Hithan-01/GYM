package com.example.Gym.Controllers;

import com.example.Gym.Entidades.Roles;
import com.example.Gym.Services.RoleService;
import com.example.Gym.Services.UserService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Register")

public class RolRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam String role) {
        try {
            System.out.println("DEBUG: Attempting to register user...");
            System.out.println("DEBUG: Username: " + username);
            System.out.println("DEBUG: Email: " + email);
            System.out.println("DEBUG: Role: " + role);
            
            userService.registerUser(username, password, email, role);
    
            System.out.println("DEBUG: Registration successful for user: " + username);
            return "redirect:/login";  // ✅ Redirect to login after success
    
        } catch (Exception e) {
            System.out.println("ERROR: Registration failed - " + e.getMessage());
            e.printStackTrace();  // ✅ Prints the full stack trace for debugging
            return "redirect:/Register?error=true"; // ✅ Redirect back to Register page
        }
    }
    

  

    @GetMapping
    public String viewRoles(Model model) {
        List<Roles> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "Register"; // Ensure roles.html exists in src/main/resources/templates/
    }
}
