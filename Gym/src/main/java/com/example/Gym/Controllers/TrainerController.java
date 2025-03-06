package com.example.Gym.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Entidades.Trainer;
import com.example.Gym.Entidades.TrainerAvailability;
import com.example.Gym.Repositories.TrainerAvailabilityRepository;
import com.example.Gym.Repositories.TrainerRepository;
import com.example.Gym.Services.RoleService;
import com.example.Gym.Services.TrainerService;
import com.example.Gym.Services.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/trainers")
@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainerAvailabilityRepository trainerAvailabilityRepository;

    @GetMapping("/dashboard/{trainerId}")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')") // ✅ Only TRAINER & ADMIN can view
    public String getTrainerDashboard(@PathVariable int trainerId, Model model) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        List<Member> members = trainerService.getTrainerMembers(trainerId);
        List<TrainerAvailability> schedule = trainerService.getTrainerSchedule(trainerId);

        model.addAttribute("trainer", trainer);
        model.addAttribute("members", members);
        model.addAttribute("schedule", schedule);

        return "TrainerDash"; // ✅ Must have a Thymeleaf template: trainerDashboard.html
    }

    // ✅ Update trainer availability
    @PostMapping("/{trainerId}/update-availability")
    @PreAuthorize("hasRole('TRAINER')") // ✅ Only Trainers can update availability
    public String updateAvailability(@PathVariable int trainerId, @RequestParam List<LocalDate> availability) {
        trainerService.updateTrainerAvailability(trainerId, availability);
        return "redirect:/trainers/dashboard/" + trainerId;
    }

    // ✅ Get trainer availability
    @GetMapping("/{trainerId}/availability")
    @ResponseBody
    public List<TrainerAvailability> getTrainerAvailability(@PathVariable int trainerId) {
        Trainer trainer = trainerService.getTrainerById(trainerId);
        return trainerAvailabilityRepository.findByTrainer(trainer);
    }

    
    @GetMapping("/add")
    public String showTrainerForm(Model model) {
        List<Trainer> trainers = trainerService.getTrainers();
    
        if (trainers.isEmpty()) {
            System.out.println("DEBUG: No trainers found in TrainerController.");
        } else {
            trainers.forEach(trainer -> System.out.println("DEBUG: Found Trainer - " + trainer.getFirstName() + " " + trainer.getLastName()));
        }
    
        model.addAttribute("users", userService.getAllUsers()); 
        model.addAttribute("trainers", trainers); // ✅ Pass trainers to view
        return "register"; 
    }
    
    @PostMapping("/add")
    public String addTrainer(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String specialization,
                             @RequestParam List<LocalDate> availability,
                             @RequestParam int experience,
                             @RequestParam String email,
                             @RequestParam String firstName,
                             @RequestParam String lastName) {
        trainerService.addTrainer(username, password, firstName, lastName, email, availability, experience, specialization);
        return "redirect:/admin/trainers";
    }
    

}
