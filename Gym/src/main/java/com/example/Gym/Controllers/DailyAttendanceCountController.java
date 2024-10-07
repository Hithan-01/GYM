package com.example.Gym.Controllers;

import com.example.Gym.Entidades.DailyAttendanceCount;
import com.example.Gym.Services.DailyAttendanceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-attendance")
public class DailyAttendanceCountController {

    @Autowired
    private DailyAttendanceCountService dailyAttendanceCountService;

    // Get all daily attendance counts
    @GetMapping
    public List<DailyAttendanceCount> getAllDailyAttendanceCounts() {
        return dailyAttendanceCountService.getAllDailyAttendanceCounts();
    }

    // Get daily attendance count by ID
    @GetMapping("/{id}")
    public DailyAttendanceCount getDailyAttendanceCountById(@PathVariable int id) {
        return dailyAttendanceCountService.getDailyAttendanceCountById(id);
    }

    // Save a new daily attendance count
    @PostMapping
    public DailyAttendanceCount createDailyAttendanceCount(@RequestBody DailyAttendanceCount dailyAttendanceCount) {
        return dailyAttendanceCountService.saveDailyAttendanceCount(dailyAttendanceCount);
    }

    // Update an existing daily attendance count
    @PutMapping("/{id}")
    public DailyAttendanceCount updateDailyAttendanceCount(@PathVariable int id, @RequestBody DailyAttendanceCount dailyAttendanceCountDetails) {
        return dailyAttendanceCountService.updateDailyAttendanceCount(id, dailyAttendanceCountDetails);
    }

    // Delete daily attendance count by ID
    @DeleteMapping("/{id}")
    public String deleteDailyAttendanceCount(@PathVariable int id) {
        dailyAttendanceCountService.deleteDailyAttendanceCount(id);
        return "Daily attendance count deleted successfully";
    }

    // Get daily attendance count for a specific date
    @GetMapping("/date/{date}")
    public DailyAttendanceCount getAttendanceCountByDate(@PathVariable String date) {
        LocalDate attendanceDate = LocalDate.parse(date);
        return dailyAttendanceCountService.getAttendanceCountByDate(attendanceDate);
    }
}
