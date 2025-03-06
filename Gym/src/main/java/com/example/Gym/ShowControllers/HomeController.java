package com.example.Gym.ShowControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home") // ✅ Define la ruta "/home" correctamente
@PreAuthorize("hasRole('ADMIN')") // 🔐 Solo ADMIN puede acceder a /home
public class HomeController {

    @GetMapping
    public String showHomePage() {
        return "Dashboard";  // ✅ Debe existir HomeTemp.html en /templates/
    }
}
