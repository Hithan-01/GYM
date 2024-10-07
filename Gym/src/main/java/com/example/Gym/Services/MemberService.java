package com.example.Gym.Services;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Obtener todos los miembros
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Puedes agregar más métodos de negocio aquí si los necesitas
}
