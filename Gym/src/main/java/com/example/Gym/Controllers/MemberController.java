package com.example.Gym.Controllers;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Endpoint para obtener todos los miembros
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
}
