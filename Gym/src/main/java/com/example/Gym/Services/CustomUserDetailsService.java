package com.example.Gym.Services;

import com.example.Gym.Entidades.User;
import com.example.Gym.Repositories.UserRepository;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;


            @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            System.out.println("Looking up user: " + username);  // Add a log
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            System.out.println("User found: " + user.getUsername());  // Add a log

            if (!user.isEnabled()) {
                throw new UsernameNotFoundException("User is disabled");
            }

            return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList())
            );
        }


}
