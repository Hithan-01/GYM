package com.example.Gym.Entidades;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_attendance_count")
public class DailyAttendanceCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "attendance_date", unique = true, nullable = false)
    private LocalDate attendanceDate;

    @Column(name = "attendance_count", nullable = false)
    private int attendanceCount;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(int attendanceCount) {
        this.attendanceCount = attendanceCount;
    }
}
