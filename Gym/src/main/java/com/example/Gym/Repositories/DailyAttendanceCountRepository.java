package com.example.Gym.Repositories;

import com.example.Gym.Entidades.DailyAttendanceCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailyAttendanceCountRepository extends JpaRepository<DailyAttendanceCount, Integer> {

    // Custom query to find daily attendance count by date
    DailyAttendanceCount findByAttendanceDate(LocalDate attendanceDate);
}
