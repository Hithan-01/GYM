package com.example.Gym.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Gym.Entidades.Payment;
import com.example.Gym.Entidades.Attendance;
import com.example.Gym.Services.PaymentService;
import com.example.Gym.Services.AttendanceService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public String adminDashboard() {
        return "admin-dashboard"; // Returns the admin dashboard view
    }

    // Manage Payments
    @GetMapping("/payments")
    public String viewPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payments"; // Returns the payments view
    }

    @GetMapping("/payments/add")
    public String addPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "add-payment"; // Returns the form to add a new payment
    }

    @PostMapping("/payments/add")
    public String addPayment(@ModelAttribute Payment payment) {
        paymentService.savePayment(payment);
        return "redirect:/admin/payments"; // Redirects to the payments list
    }

    // Manage Attendance
    @GetMapping("/attendance")
    public String viewAttendance(Model model) {
        List<Attendance> attendanceRecords = attendanceService.getAllAttendanceRecords();
        model.addAttribute("attendanceRecords", attendanceRecords);
        return "attendance"; // Returns the attendance view
    }

    @GetMapping("/attendance/add")
    public String addAttendanceForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "add-attendance"; // Returns the form to add new attendance
    }

    @PostMapping("/attendance/add")
    public String addAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/admin/attendance"; // Redirects to the attendance list
    }
}
