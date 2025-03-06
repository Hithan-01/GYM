package com.example.Gym.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Gym.Entidades.Attendance;
import com.example.Gym.Services.AttendanceService;

import java.util.List;

@Controller
@RequestMapping("/admin/attendance")
@PreAuthorize("isAuthenticated()")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // View all attendance records
    @GetMapping
    public String viewAttendance(Model model) {
        List<Attendance> attendanceRecords = attendanceService.getAllAttendanceRecords();
        model.addAttribute("attendanceRecords", attendanceRecords);
        return "attendance"; // Returns the attendance view
    }

    // Add new attendance
    @GetMapping("/add")
    public String addAttendanceForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "add-attendance"; // Returns the form to add new attendance
    }

    @PostMapping("/add")
    public String addAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/admin/attendance"; // Redirects to the attendance list
    }
}
