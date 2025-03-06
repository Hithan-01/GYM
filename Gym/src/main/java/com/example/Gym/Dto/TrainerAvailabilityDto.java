package com.example.Gym.Dto;

import java.time.LocalDate;
public class TrainerAvailabilityDto {
    private int id;
    private int trainerId;
    private LocalDate availableDate;

    public TrainerAvailabilityDto() {}

    public TrainerAvailabilityDto(int id, int trainerId, LocalDate availableDate) {
        this.id = id;
        this.trainerId = trainerId;
        this.availableDate = availableDate;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }

    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
}
