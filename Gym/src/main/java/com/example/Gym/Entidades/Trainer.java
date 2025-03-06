package com.example.Gym.Entidades;



import java.util.ArrayList;
import java.util.List;
import com.example.Gym.Entidades.TrainerAvailability;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  trainerId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Assuming you have a User entity

    private String specialization;

   @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerAvailability> availabilities = new ArrayList<>(); // ✅ Linked availability
    
    private int experience;
  @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    public int  getTrainerId() {
        return trainerId;
    }
    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
    

public int getExperience() {
    return experience;
}
public void setExperience(int experience) {
    this.experience = experience;
}
public String getSpecialization() {
    return specialization;
}
public void setSpecialization(String specialization) {
    this.specialization = specialization;
}
// Removed duplicate methods
public User getUser() {
    return user;

}
public void setUser(User user) {
    this.user = user;
}

public String getFirstName() {
    return firstName;
}
public void setFirstName(String firstName) {
    this.firstName = firstName;

}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;

}

public List<TrainerAvailability> getAvailabilities() {
    return availabilities;
}
public void setAvailabilities(List<TrainerAvailability> availabilities) {
    this.availabilities = availabilities;
}

}