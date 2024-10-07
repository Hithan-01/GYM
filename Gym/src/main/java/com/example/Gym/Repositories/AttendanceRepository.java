package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByMember_MemberId(int memberId);


    // Custom query to find attendance records by attendance date
    List<Attendance> findByAttendanceDate(LocalDateTime attendanceDate);
}
