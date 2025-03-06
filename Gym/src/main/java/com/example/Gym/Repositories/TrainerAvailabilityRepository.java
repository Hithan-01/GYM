package com.example.Gym.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gym.Entidades.Trainer;
import com.example.Gym.Entidades.TrainerAvailability;

@Repository
public interface TrainerAvailabilityRepository extends JpaRepository<TrainerAvailability, Long> {
    List<TrainerAvailability> findByTrainer(Trainer trainer);
    void deleteByTrainer(Trainer trainer); // ✅ Deletes all availability for a trainer
}
