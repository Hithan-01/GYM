package com.example.Gym.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login"; // 🔀 Redirigir automáticamente a la pantalla de login
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 🔀 Asegurar que Thymeleaf carga login.html
    }
}
