package com.example.Gym.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Gym.Entidades.DailyAttendanceCount;
import com.example.Gym.Repositories.DailyAttendanceCountRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional // Use this for transaction management
public class DailyAttendanceCountService {

    @Autowired
    private DailyAttendanceCountRepository dailyAttendanceCountRepository;

    // Get all daily attendance counts
    public List<DailyAttendanceCount> getAllDailyAttendanceCounts() {
        return dailyAttendanceCountRepository.findAll();
    }

    // Get daily attendance count by ID
    public DailyAttendanceCount getDailyAttendanceCountById(int id) {
        return dailyAttendanceCountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Daily attendance count not found"));
    }

    // Save a new daily attendance count
    public DailyAttendanceCount saveDailyAttendanceCount(DailyAttendanceCount dailyAttendanceCount) {
        return dailyAttendanceCountRepository.save(dailyAttendanceCount);
    }

    // Update an existing daily attendance count
    public DailyAttendanceCount updateDailyAttendanceCount(int id, DailyAttendanceCount dailyAttendanceCountDetails) {
        DailyAttendanceCount existingCount = dailyAttendanceCountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Daily attendance count not found"));

        // Update fields
        existingCount.setAttendanceDate(dailyAttendanceCountDetails.getAttendanceDate());
        existingCount.setAttendanceCount(dailyAttendanceCountDetails.getAttendanceCount());
        
        return dailyAttendanceCountRepository.save(existingCount);
    }

    // Delete daily attendance count by ID
    public void deleteDailyAttendanceCount(int id) {
        DailyAttendanceCount existingCount = dailyAttendanceCountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Daily attendance count not found"));
        dailyAttendanceCountRepository.delete(existingCount);
    }

    // Get daily attendance count for a specific date
    public DailyAttendanceCount getAttendanceCountByDate(LocalDate date) {
        return dailyAttendanceCountRepository.findByAttendanceDate(date); // Assuming this method is defined in your DailyAttendanceCountRepository
    }

    // Additional business logic can be added here as needed
}
