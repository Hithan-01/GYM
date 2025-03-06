package com.example.Gym.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Gym.Dto.MemberDto;
import com.example.Gym.Dto.TrainerAvailabilityDto;
import com.example.Gym.Dto.TrainerDto;
import com.example.Gym.Entidades.Member;
import com.example.Gym.Entidades.Roles;
import com.example.Gym.Entidades.Trainer;
import com.example.Gym.Entidades.TrainerAvailability;
import com.example.Gym.Entidades.User;
import com.example.Gym.Repositories.RolesRepository;
import com.example.Gym.Repositories.TrainerAvailabilityRepository;
import com.example.Gym.Repositories.TrainerRepository;
import com.example.Gym.Repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final MemberService memberService;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final TrainerAvailabilityRepository trainerAvailabilityRepository;
    private final UserRepository userRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, MemberService memberService, RolesRepository rolesRepository,
                          PasswordEncoder passwordEncoder, TrainerAvailabilityRepository trainerAvailabilityRepository,
                          UserRepository userRepository) {
        this.trainerRepository = trainerRepository;
        this.memberService = memberService;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.trainerAvailabilityRepository = trainerAvailabilityRepository;
        this.userRepository = userRepository;
    }

    private MemberDto convertToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(member.getMemberId());
        memberDto.setFirstName(member.getFirstName());
        memberDto.setTrainerId(member.getTrainer().getTrainerId());
        return memberDto;
    }

    public  TrainerDto convertToDto(Trainer trainer) {
    List<LocalDate> availabilityDates = trainerAvailabilityRepository.findByTrainer(trainer)
            .stream()
            .map(TrainerAvailability::getAvailableDate)
            .collect(Collectors.toList());

    TrainerDto trainerDto = new TrainerDto();
    trainerDto.setTrainerId(trainer.getTrainerId());
    trainerDto.setUsername(trainer.getUser().getUsername());
    trainerDto.setSpecialization(trainer.getSpecialization());
    trainerDto.setExperience(trainer.getExperience());
    trainerDto.setAvailability(availabilityDates);
    return trainerDto;
}

public TrainerAvailabilityDto convertToDto(TrainerAvailability availability) {
    return new TrainerAvailabilityDto(
            availability.getId(),  
            availability.getTrainer().getTrainerId(),
            availability.getAvailableDate()
    );
}


                public List<Member> getTrainerMembers(int trainerId) {
                    return trainerRepository.findMembersByTrainerId(trainerId);
                }

                public List<Trainer> getTrainers() {
                    List<Trainer> trainers = trainerRepository.findAllTrainers();
                
                    System.out.println("🔍 DEBUG: Number of trainers found: " + trainers.size());
                
                    if (trainers.isEmpty()) {
                        System.out.println("❌ DEBUG: No trainers found in database.");
                    } else {
                        trainers.forEach(trainer -> 
                            System.out.println("✅ Trainer - ID: " + trainer.getTrainerId() + 
                                                ", Name: " + trainer.getFirstName() + " " + trainer.getLastName()));
                    }
                
                    return trainers;
                }
                
                
    public Trainer getTrainerById(int trainerId) {
        return trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
    }

    public List<TrainerAvailability> getTrainerSchedule(int trainerId) {
        Trainer trainer = getTrainerById(trainerId);
        return trainerAvailabilityRepository.findByTrainer(trainer);
    }

    public void assignTrainerToMember(int memberId, int trainerId) {
        Member member = memberService.getMemberByid(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        member.setTrainer(trainer);
        MemberDto memberDto = convertToDto(member);
        memberService.saveMember(memberDto);
    }

    public void updateTrainerAvailability(int trainerId, List<LocalDate> dates) {
        Trainer trainer = getTrainerById(trainerId);
        trainerAvailabilityRepository.deleteByTrainer(trainer); // Remove old availability

        for (LocalDate date : dates) {
            TrainerAvailability availability = new TrainerAvailability();
            availability.setTrainer(trainer);
            availability.setAvailableDate(date);
            trainerAvailabilityRepository.save(availability);
        }
    }
    @Transactional
    public void addTrainer(String username, String password, String firstName, String lastName, 
                        String specialization, List<LocalDate> availability, int experience, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists!");
        }
    
        // Fetch the trainer role
        Roles trainerRole = rolesRepository.findByName("ROLE_TRAINER")
                .orElseThrow(() -> new RuntimeException("ROLE_TRAINER not found in database"));
    
        // Create and save the user first
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setEnabled(true);
        user.getRoles().add(trainerRole);
        user = userRepository.save(user); // ✅ Save user first
    
        // Now add user to trainers table
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setSpecialization(specialization);
        trainer.setExperience(experience);
    
        trainer = trainerRepository.save(trainer); // ✅ Save trainer
    
        // Save trainer availability
        for (LocalDate date : availability) {
            TrainerAvailability availabilityEntry = new TrainerAvailability();
            availabilityEntry.setTrainer(trainer);
            availabilityEntry.setAvailableDate(date);
            trainerAvailabilityRepository.save(availabilityEntry);
        }
    }
    
    
    
}
