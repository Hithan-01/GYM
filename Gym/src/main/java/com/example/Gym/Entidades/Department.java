package com.example.Gym.Entidades;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "departments")

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Matches database type (bigint or int)

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<EmployeeDetails> employees; // Relationship with Employee

    public List<EmployeeDetails> getEmployees() {
        return employees;
    }
    public void setEmployees(List<EmployeeDetails> employees) {
        this.employees = employees;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

