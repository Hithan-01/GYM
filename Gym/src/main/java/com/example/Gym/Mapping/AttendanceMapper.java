package com.example.Gym.Mapping;

import org.mapstruct.Mapper;

import com.example.Gym.Dto.AttendanceDto;
import com.example.Gym.Entidades.Attendance;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    AttendanceDto toDTO(Attendance attendance);

    Attendance toEntity(AttendanceDto attendanceDTO);
}
