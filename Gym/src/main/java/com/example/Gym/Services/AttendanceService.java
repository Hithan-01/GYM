package com.example.Gym.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Gym.Entidades.Attendance;
import com.example.Gym.Repositories.AttendanceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional // Use this for transaction management
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Get all attendance records
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    // Get attendance record by ID
    public Attendance getAttendanceById(int id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
    }

    // Save a new attendance record
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Update an existing attendance record
    public Attendance updateAttendance(int id, Attendance attendanceDetails) {
        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));

        // Update fields
        existingAttendance.setAttendanceDate(attendanceDetails.getAttendanceDate());
        existingAttendance.setCheckInTime(attendanceDetails.getCheckInTime());
        existingAttendance.setCheckOutTime(attendanceDetails.getCheckOutTime());
        existingAttendance.setPaymentStatus(attendanceDetails.getPaymentStatus());
        
        return attendanceRepository.save(existingAttendance);
    }

    // Delete attendance record by ID
    public void deleteAttendance(int id) {
        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        attendanceRepository.delete(existingAttendance);
    }

    // Get attendance records by member ID
    public List<Attendance> getAttendanceByMemberId(int memberId) {
        return attendanceRepository.findByMemberId(memberId); // Assuming this method is defined in your AttendanceRepository
    }

    // Get attendance records for a specific date
    public List<Attendance> getAttendanceByDate(LocalDateTime date) {
        return attendanceRepository.findByAttendanceDate(date); // Assuming this method is defined in your AttendanceRepository
    }

    // Additional business logic can be added here as needed
}
