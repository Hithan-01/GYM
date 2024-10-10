package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // Additional query methods can be defined here if needed
}
