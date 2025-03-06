package com.example.Gym.Mapping;

import com.example.Gym.Dto.EmployeeDetailsDTO;
import com.example.Gym.Entidades.Department;
import com.example.Gym.Entidades.EmployeeDetails;
import com.example.Gym.Entidades.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class EmployeeDetailsMapper {

    
    private EmployeeDetailsDTO dto;
  
    public EmployeeDetailsDTO toDTO(EmployeeDetails employeeDetails) {
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        
        employeeDetailsDTO.setUserId(employeeDetails.getUser() != null ? employeeDetails.getUser().getId() : null);
        employeeDetailsDTO.setFirstName(employeeDetails.getFirstName());
        employeeDetailsDTO.setLastName(employeeDetails.getLastName());
        employeeDetailsDTO.setJobTitle(employeeDetails.getJobTitle());
        employeeDetailsDTO.setSalary(employeeDetails.getSalary());
        employeeDetailsDTO.setEmploymentType(employeeDetails.getEmploymentType());
        
        // ✅ Fix: Extract department ID from Department entity
        if (employeeDetails.getDepartment() != null) {
            employeeDetailsDTO.setDepartmentId(employeeDetails.getDepartment().getId());
        }
    
        employeeDetailsDTO.setIsTrainer(employeeDetails.getIsTrainer());
        employeeDetailsDTO.setHireDate(employeeDetails.getHireDate());
        employeeDetailsDTO.setTerminationDate(employeeDetails.getTerminationDate());
        
        return employeeDetailsDTO;
    }
    

    public EmployeeDetails toEntity(EmployeeDetailsDTO dto, User user, Department department) {
        return new EmployeeDetails(
                user,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getJobTitle(),
                dto.getSalary(),
                dto.getEmploymentType(),
                department, // Ensure this is a Department entity, not an ID
                dto.getIsTrainer(),
                dto.getHireDate(),
                dto.getTerminationDate()
        );
    }
    
}
