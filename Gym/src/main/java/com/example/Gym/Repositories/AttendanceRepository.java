package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    // Custom query to find attendance records by member ID
    List<Attendance> findByMemberId(int memberId);

    // Custom query to find attendance records by attendance date
    List<Attendance> findByAttendanceDate(LocalDateTime attendanceDate);
}
