package com.example.Gym.Dto;

import java.time.LocalDate;

public class AttendanceDto {

    private int attendanceId;
    private int memberId;
    private LocalDate attendanceDate;
    private boolean present;

    // Constructors
    public AttendanceDto() {}

    public AttendanceDto(int attendanceId, int memberId, LocalDate attendanceDate, boolean present) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.attendanceDate = attendanceDate;
        this.present = present;
    }

    // Getters and Setters
    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }
}
