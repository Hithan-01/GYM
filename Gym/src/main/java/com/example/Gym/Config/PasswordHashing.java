package com.example.Gym.Config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashing {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";  // Replace this with the password you want to hash
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
