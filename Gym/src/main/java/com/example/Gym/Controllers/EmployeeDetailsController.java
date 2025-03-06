package com.example.Gym.Controllers;

import com.example.Gym.Dto.EmployeeDetailsDTO;
import com.example.Gym.Entidades.Department;
import com.example.Gym.Services.DepartmentService;
import com.example.Gym.Services.EmployeeDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/employees")



public class EmployeeDetailsController {


private DepartmentService DepartmentService;

    private final EmployeeDetailsService employeeDetailsService;

    public EmployeeDetailsController(EmployeeDetailsService employeeDetailsService, DepartmentService DepartmentService) {
        this.DepartmentService = DepartmentService;
        this.employeeDetailsService = employeeDetailsService;
    }

    // ✅ Show Employee List Page

  
    // public String listEmployees(Model model) {
    //     List<EmployeeDetailsDTO> employees = employeeDetailsService.getAllEmployees();
    //     model.addAttribute("employees", employees);
    //     return "Employees/EmployeeDetails";  
    // }
    

@GetMapping
public String listEmployees(Model model) {
    List<EmployeeDetailsDTO> employees = employeeDetailsService.getAllEmployees();
    model.addAttribute("employees", employees);
    return "Employees/EmployeeDetails";  // Returns "employee-list.html"
}

@GetMapping("/edit/{userId}")
public String showEditForm(@PathVariable Long userId, Model model) {
    EmployeeDetailsDTO employeeDetails = employeeDetailsService.getEmployeeDetails(userId);
    model.addAttribute("employeeDetails", employeeDetails);

    // Fetch departments from the database
    List<Department> departments = DepartmentService.getAllDepartments();
    model.addAttribute("departments", departments);

    return "Employees/AddEmployees";  // Use the same form for editing employees
}


    // ✅ Show Add New Employee Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employeeDetails", new EmployeeDetailsDTO());
    
        // ✅ Fetch departments from the database
        List<Department> departments = DepartmentService.getAllDepartments();
        System.out.println("DEBUG: Passing departments to view: " + departments.size()); // Extra Debugging
        model.addAttribute("departments", departments); 
    
        return "Employees/AddEmployees";  // Ensure this matches your Thymeleaf filename
    }
    
    // ✅ Handle Save Employee (Create or Update)
  @PostMapping("/save")
public String saveEmployee(@ModelAttribute EmployeeDetailsDTO dto, RedirectAttributes redirectAttributes) {
    try {
        employeeDetailsService.createOrUpdateEmployeeDetails(dto.getUserId(), dto);
        redirectAttributes.addFlashAttribute("successMessage", "Employee created successfully!");
        return "redirect:/admin/employees";  // Redirect back to employee list
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
        return "redirect:/admin/employees/add";  // Stay on the add page and show error
    }
}

    // ✅ Handle Delete Employee
    @PostMapping("/delete/{userId}")
    public String deleteEmployee(@PathVariable Long userId) {
        employeeDetailsService.deleteEmployeeDetails(userId);
        return "redirect:/admin/employees";  // Redirect back to employee list
    }
}
