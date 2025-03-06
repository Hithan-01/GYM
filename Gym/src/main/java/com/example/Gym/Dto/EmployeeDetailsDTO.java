package com.example.Gym.Dto;

import com.example.Gym.Entidades.Department;
import com.example.Gym.Enums.EmploymentType;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDetailsDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private BigDecimal salary;
    private EmploymentType employmentType;
  
    private Boolean isTrainer;
    private LocalDate hireDate;
    private LocalDate terminationDate;
    private Long departmentId; // ✅ Use department ID instead of entity

    // ✅ No-args constructor
    public EmployeeDetailsDTO() {}

    // ✅ All-args constructor
    public EmployeeDetailsDTO(Long userId, String firstName, String lastName, String jobTitle, 
                              BigDecimal salary, EmploymentType employmentType, Long departmentId, 
                              Boolean isTrainer, LocalDate hireDate, LocalDate terminationDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.employmentType = employmentType;
        this.departmentId = departmentId;
        this.isTrainer = isTrainer;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
    }

    // ✅ Getters
    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }
    public Long getDepartmentId() { return departmentId; }

    public Boolean getIsTrainer() {
        return isTrainer;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    // ✅ Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    
    public void setIsTrainer(Boolean isTrainer) {
        this.isTrainer = isTrainer;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }
}
