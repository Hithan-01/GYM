package com.example.Gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Gym.Dto.MemberDto;
import com.example.Gym.Entidades.Trainer;
import com.example.Gym.Services.MemberService;
import com.example.Gym.Services.TrainerService;

@Controller
@RequestMapping("/admin/members")
public class MembersAdminController {

    @Autowired
    private TrainerService trainerService;
    

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String listMembers(Model model) {
        List<MemberDto> members = memberService.findAllMembers();
        
        if (members == null || members.isEmpty()) {
            System.out.println("No members found or list is null.");
        } else {
            members.forEach(member -> {
                if (member != null) {
                    System.out.println("Member ID: " + member.getMemberId());
                } else {
                    System.out.println("Found a null member in the list.");
                }
            });
        }
        
        model.addAttribute("members", members);
        return "Members/Members";
    }

 @GetMapping("/add")
public String showAddMemberForm(Model model) {
    List<Trainer> trainers = trainerService.getTrainers();
    
    // Debugging
    System.out.println("🔍 DEBUG: Trainers sent to Thymeleaf: " + trainers.size());
    
    if (trainers.isEmpty()) {
        System.out.println("❌ DEBUG: No trainers found in MemberController.");
    } else {
        trainers.forEach(trainer -> 
            System.out.println("✅ DEBUG: Trainer - " + trainer.getFirstName() + " " + trainer.getLastName()));
    }

    model.addAttribute("trainers", trainers); // ✅ Pass trainers to Thymeleaf
    return "AddMember";  
}

    
}
