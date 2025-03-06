package com.example.Gym.Controllers;

import com.example.Gym.Services.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    // API to create payment
    @PostMapping("/create")
    @ResponseBody
    public Map<String, String> createPayment(@RequestParam String title, @RequestParam Double amount) {
        Map<String, String> response = new HashMap<>();
        try {
            String paymentUrl = mercadoPagoService.createPayment(title, amount);
            response.put("paymentUrl", paymentUrl);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }

    // Show payment page with a button
    @GetMapping("/pay")
    public String showPaymentPage() {
        return "PaymentPage"; // This will load the "payment.html" template
    }
}
