package com.example.Gym.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Entidades.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    @Query("SELECT t FROM Trainer t")
    List<Trainer> findAllTrainers();
    

    @Query("SELECT m FROM Member m WHERE m.trainer.trainerId = :trainerId")
    List<Member> findMembersByTrainerId(@Param("trainerId") int trainerId);
}

