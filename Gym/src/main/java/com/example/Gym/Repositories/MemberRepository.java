package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // Aquí puedes agregar consultas personalizadas si lo necesitas
}