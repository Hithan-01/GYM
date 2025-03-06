package com.example.Gym.Controllers;

import com.example.Gym.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-test")
    public String registerTest(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        userService.registerUser(username, password, email, email);
        return "User registered successfully!";
    }
}
