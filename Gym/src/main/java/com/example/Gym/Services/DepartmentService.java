package com.example.Gym.Services;

import com.example.Gym.Entidades.Department;
import com.example.Gym.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // ✅ Create a new department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // ✅ Get all departments
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        System.out.println("DEBUG: Found " + departments.size() + " departments.");
        return departments;
    }
    
    // ✅ Get a department by ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // ✅ Update a department
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(updatedDepartment.getName());
        return departmentRepository.save(department);
    }

    // ✅ Delete a department
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
