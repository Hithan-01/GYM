package com.example.Gym;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.Gym.Entidades.Attendance;
import com.example.Gym.Repositories.AttendanceRepository;
import com.example.Gym.Services.AttendanceService;

import java.util.Optional;

public class AttendanceServiceTest {

    @InjectMocks
    private AttendanceService attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAttendanceById() {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(1);

        when(attendanceRepository.findById(1)).thenReturn(Optional.of(attendance));
        Attendance found = attendanceService.getAttendanceById(1);
        assertEquals(1, found.getAttendanceId());
    }

    // Additional tests can be added here
}
