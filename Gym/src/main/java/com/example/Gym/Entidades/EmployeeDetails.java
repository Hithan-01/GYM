package com.example.Gym.Entidades;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.Gym.Enums.EmploymentType;

@Entity
@Table(name = "employee_details")

public class EmployeeDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = true) // ✅ Allow User to be NULL
    private User user;  // ✅ This was causing the error


    private String firstName;
    private String lastName;
    private String jobTitle;
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentType employmentType;


    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_employee_department"))
    private Department department;  // Foreign key relationship


    private Boolean isTrainer = false;
    private LocalDate hireDate;
    private LocalDate terminationDate;




    public EmployeeDetails(User user, String firstName, String lastName, String jobTitle, 
    BigDecimal salary, EmploymentType employmentType, 
    Department department, Boolean isTrainer, 
    LocalDate hireDate, LocalDate terminationDate) {
this.user = user;
this.firstName = firstName;
this.lastName = lastName;
this.jobTitle = jobTitle;
this.salary = salary;
this.employmentType = employmentType;
this.department = department;
this.isTrainer = isTrainer;
this.hireDate = hireDate;
this.terminationDate = terminationDate;
}

public EmployeeDetails() {
        
    }

public Department getDepartment() {
    return department;
}
public void setDepartment(Department department) {
    this.department = department;
}
    public EmploymentType getEmploymentType() {
        return employmentType;
    }
    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public LocalDate getTerminationDate() {
        return terminationDate;
    }
    public User getUser() {
        return user;
    }
    public String getLastName() {
        return lastName;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public Boolean getIsTrainer() {
        return isTrainer;
    }
    public void setIsTrainer(Boolean isTrainer) {
        this.isTrainer = isTrainer;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
