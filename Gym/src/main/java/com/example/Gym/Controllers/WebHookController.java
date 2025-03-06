package com.example.Gym.Controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

 
    @PostMapping
    public void handleWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Received webhook: " + payload);
    }
}
