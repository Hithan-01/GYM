package com.example.Gym.RestControllers;

import com.example.Gym.Dto.EmployeeDetailsDTO;
import com.example.Gym.Entidades.EmployeeDetails;
import com.example.Gym.Services.EmployeeDetailsService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeDetailsRestController {

    private final EmployeeDetailsService employeeDetailsService;

    // ✅ Constructor-based Dependency Injection
    public EmployeeDetailsRestController(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EmployeeDetailsDTO> getEmployeeDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(employeeDetailsService.getEmployeeDetails(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createOrUpdateEmployeeDetails(
            @PathVariable Long userId, @RequestBody EmployeeDetailsDTO dto) {
        try {
            System.out.println("DEBUG: Received request to create/update employee for user ID: " + userId);
            System.out.println("DEBUG: Employee DTO -> " + dto);
    
            EmployeeDetailsDTO savedEmployee = employeeDetailsService.createOrUpdateEmployeeDetails(userId, dto);
    
            System.out.println("DEBUG: Employee saved successfully: " + savedEmployee);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            System.err.println("ERROR: Failed to save employee - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save employee: " + e.getMessage());
        }
    }


            @PostMapping("/create")
            public ResponseEntity<?> createEmployee(@RequestBody EmployeeDetailsDTO dto) {
                try {
                    System.out.println("DEBUG: Creating new employee: " + dto);

                    // ✅ Do NOT pass userId here (let the database handle it)
                    EmployeeDetailsDTO savedEmployee = employeeDetailsService.createOrUpdateEmployeeDetails(null, dto);

                    System.out.println("DEBUG: Employee created successfully: " + savedEmployee);
                    return ResponseEntity.ok(savedEmployee);
                } catch (Exception e) {
                    System.err.println("ERROR: Failed to create employee - " + e.getMessage());

                    // ✅ Return a valid JSON error response
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("error", "Failed to create employee");
                    errorResponse.put("message", e.getMessage());

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
                }
}




    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteEmployeeDetails(@PathVariable Long userId) {
        employeeDetailsService.deleteEmployeeDetails(userId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Assign department to an employee
    @PutMapping("/{userId}/assign-department/{departmentId}")
    public ResponseEntity<EmployeeDetails> assignDepartment(
            @PathVariable Long userId, @PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeDetailsService.assignDepartment(userId, departmentId));
    }
}
