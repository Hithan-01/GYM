package com.example.Gym.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Gym.Entidades.Payment;
import com.example.Gym.Services.PaymentService;

import java.util.List;

@Controller
@RequestMapping("/admin/payments")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    // View all payments
    @GetMapping
    public String viewPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payments"; // Returns the payments view
    }

    // Add a new payment
    @GetMapping("/add")
    public String addPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "add-payment"; // Returns the form to add a new payment
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/admin/payments"; // Redirects to the payments list
    }
}
