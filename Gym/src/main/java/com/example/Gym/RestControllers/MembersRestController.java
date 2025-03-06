package com.example.Gym.RestControllers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

import com.example.Gym.Dto.MemberDto;
import com.example.Gym.Dto.TrainerDto;
import com.example.Gym.Entidades.Trainer;
import com.example.Gym.Services.MemberService;
import com.example.Gym.Services.TrainerService;
import com.example.Gym.Entidades.TrainerAvailability;

@RestController
@RequestMapping("/api/members")
@PreAuthorize("isAuthenticated()")
public class MembersRestController {

    

    @Autowired
    private MemberService memberService;

    @Autowired
    private TrainerService trainerService;

    public String listMembers(Model model) {
        List<MemberDto> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "Members/Members"; // This should be the name of your Thymeleaf template
    }

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        if (memberDto.getDateOfBirth() == null) {
            // Instead of returning a ResponseEntity<String>, throw an exception or handle it appropriately
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date of Birth is required");
        }
        MemberDto createdMember = memberService.saveMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable int id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable int id, @RequestBody MemberDto memberDto) {
        MemberDto updatedMember = memberService.updateMember(id, memberDto);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Integer memberId) {
        try {
            memberService.deleteMemberById(memberId); // Delete by memberId
            return ResponseEntity.ok("Member deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found with ID: " + memberId);
        }
    }

            @PostMapping("/{id}/suspend")
        public ResponseEntity<MemberDto> suspendMember(@PathVariable int id) {
            // Implement your logic to suspend the member
            MemberDto suspendedMember = memberService.suspendMember(id);
            return ResponseEntity.ok(suspendedMember);
        }

        @GetMapping("/all")
        public List<TrainerDto> getAllTrainers() {
            List<Trainer> trainers = trainerService.getTrainers();
        
            if (trainers.isEmpty()) {
                System.out.println("❌ DEBUG: No trainers found.");
            } else {
                trainers.forEach(trainer -> 
                    System.out.println("✅ DEBUG: Trainer - " + trainer.getFirstName() + " " + trainer.getLastName()));
            }
        
            List<TrainerDto> trainerDtos = new ArrayList<>();
            trainers.forEach(trainer -> {
                TrainerDto trainerDto = new TrainerDto();
                trainerDto.setTrainerId(trainer.getTrainerId());
                trainerDto.setFirstName(trainer.getFirstName());
                trainerDto.setLastName(trainer.getLastName());
                trainerDto.setSpecialization(trainer.getSpecialization());
                trainerDto.setExperience(trainer.getExperience());
        
                // Get Availability Dates
                List<LocalDate> availability = trainer.getAvailabilities().stream()
                                                    .map(TrainerAvailability::getAvailableDate)
                                                    .toList();
                trainerDto.setAvailability(availability);
        
                trainerDtos.add(trainerDto);
            });
        
            return trainerDtos; 
        }
}        

            

        
    
