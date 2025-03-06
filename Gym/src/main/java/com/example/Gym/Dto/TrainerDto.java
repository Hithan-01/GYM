package com.example.Gym.Dto;

import java.time.LocalDate;
import java.util.List;

public class TrainerDto {
    private int  trainerId;
    private String username; // Get from linked User entity
    private String specialization;
    private int experience;
    private List<LocalDate> availability; // List of available dates
    private String firstName; // Get from linked User entity
    private String lastName; // Get from linked User entity


    // Constructors
    public TrainerDto() {}

    public TrainerDto(int trainerId, String username, String specialization, int experience, List<LocalDate> availability, String firstName, String lastName) {
        this.trainerId = trainerId;
        this.username = username;
        this.specialization = specialization;
        this.experience = experience;
        this.availability = availability;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    // Getters & Setters
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int  trainerId) { this.trainerId = trainerId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public List<LocalDate> getAvailability() { return availability; }
    public void setAvailability(List<LocalDate> availability) { this.availability = availability; }
}
