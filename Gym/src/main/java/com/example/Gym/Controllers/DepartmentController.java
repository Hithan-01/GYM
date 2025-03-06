package com.example.Gym.Controllers;

import com.example.Gym.Entidades.Department;
import com.example.Gym.Services.DepartmentService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "Departments/departments"; // ✅ Ensure lowercase matches the filename exactly
    }
    
    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "Departments/department-add"; // ✅ Matches "department-add.html" in templates

    }

    // ✅ Process adding a new department
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.createDepartment(department);
        return "redirect:/admin/departments";
    }

    // ✅ Show edit form
    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "Departments/Edit"; // departments/edit.html
    }

    // ✅ Process updating a department
    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute Department department) {
        departmentService.updateDepartment(id, department);
        return "redirect:/admin/departments";
    }

    // ✅ Delete department
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/admin/departments";
    }
}
