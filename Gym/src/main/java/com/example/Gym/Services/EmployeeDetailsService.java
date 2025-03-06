package com.example.Gym.Services;

import com.example.Gym.Dto.EmployeeDetailsDTO;
import com.example.Gym.Entidades.Department;
import com.example.Gym.Entidades.EmployeeDetails;
import com.example.Gym.Entidades.User;
import com.example.Gym.Mapping.EmployeeDetailsMapper;
import com.example.Gym.Repositories.DepartmentRepository;
import com.example.Gym.Repositories.EmployeeDetailsRepository;
import com.example.Gym.Repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeDetailsService {


    @Autowired
    private DepartmentRepository departmentRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final UserRepository userRepository;
    private final EmployeeDetailsMapper employeeDetailsMapper;

    public EmployeeDetailsService(EmployeeDetailsRepository employeeDetailsRepository, UserRepository userRepository, EmployeeDetailsMapper employeeDetailsMapper) {
        this.employeeDetailsRepository = employeeDetailsRepository;
        this.userRepository = userRepository;
        this.employeeDetailsMapper = employeeDetailsMapper;
    }
 

    public EmployeeDetails assignDepartment(Long employeeId, Long departmentId) {
        EmployeeDetails employee = employeeDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);
        return employeeDetailsRepository.save(employee);
    
    }
    
    public EmployeeDetailsDTO getEmployeeDetails(Long userId) {
        EmployeeDetails employeeDetails = employeeDetailsRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Employee details not found for user ID: " + userId));
        return employeeDetailsMapper.toDTO(employeeDetails);
    }


      public List<EmployeeDetailsDTO> getAllEmployees() {
        List<EmployeeDetails> employees = employeeDetailsRepository.findAll();
        return employees.stream()
                .map(employeeDetailsMapper::toDTO)
                .collect(Collectors.toList());

            }
            @Transactional
            public EmployeeDetailsDTO createOrUpdateEmployeeDetails(Long userId, EmployeeDetailsDTO dto) {
                try {
                    System.out.println("DEBUG: Received Employee DTO -> " + dto);
            
                    if (dto.getDepartmentId() == null) {
                        throw new RuntimeException("ERROR: Department ID is missing");
                    }
            
                    // ✅ Fetch department
                    Department department = departmentRepository.findById(dto.getDepartmentId())
                            .orElseThrow(() -> new EntityNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
            
                    EmployeeDetails employeeDetails;
                    User user = null;
            
                    if (userId != null) {
                        // ✅ UPDATE EXISTING EMPLOYEE
                        employeeDetails = employeeDetailsRepository.findByUserId(userId)
                                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
                        user = employeeDetails.getUser();  // ✅ Preserve existing user
                    } else {
                        // ✅ CREATE NEW EMPLOYEE
                        employeeDetails = new EmployeeDetails();
                        // ❌ Do NOT assign a user here if it's not required
                    }
            
                    employeeDetails.setUser(user);  // ✅ Allow null user
                    employeeDetails.setFirstName(dto.getFirstName());
                    employeeDetails.setLastName(dto.getLastName());
                    employeeDetails.setJobTitle(dto.getJobTitle());
                    employeeDetails.setSalary(dto.getSalary());
                    employeeDetails.setEmploymentType(dto.getEmploymentType());
                    employeeDetails.setDepartment(department);
                    employeeDetails.setIsTrainer(dto.getIsTrainer());
                    employeeDetails.setHireDate(dto.getHireDate());
                    employeeDetails.setTerminationDate(dto.getTerminationDate());
            
                    EmployeeDetails savedEmployee = employeeDetailsRepository.save(employeeDetails);
                    System.out.println("DEBUG: Employee saved successfully: " + savedEmployee);
                    
                    return employeeDetailsMapper.toDTO(savedEmployee);
                } catch (Exception e) {
                    System.err.println("ERROR: Failed to save employee - " + e.getMessage());
                    throw new RuntimeException("Failed to save employee", e);
                }
            }
            
                    
            

    public void deleteEmployeeDetails(Long userId) {
        employeeDetailsRepository.findByUserId(userId)
                .ifPresent(employeeDetailsRepository::delete);
    }
}
