package com.example.Gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Gym.Repositories.MemberRepository;

@Component
public class TestDataBaseConnection implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing database connection...");
        System.out.println("Members count: " + memberRepository.count());
    }
}
