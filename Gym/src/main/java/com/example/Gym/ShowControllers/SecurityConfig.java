package com.example.Gym.ShowControllers;

import com.example.Gym.Services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 🚨 Disable CSRF (only in development)
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives(
                        "default-src 'self'; " +
                        "script-src 'self' 'unsafe-inline' 'unsafe-eval' https://code.jquery.com https://maxcdn.bootstrapcdn.com https://cdn.jsdelivr.net; " +
                        "style-src 'self' 'unsafe-inline' https://maxcdn.bootstrapcdn.com https://cdn.jsdelivr.net; " +
                        "font-src 'self' https://fonts.googleapis.com https://fonts.gstatic.com; " +
                        "img-src 'self' data:; " +
                        "connect-src 'self'; "
                    )
                )
            )
            .authorizeHttpRequests(authz -> authz
            .requestMatchers("/test-trainers").permitAll()
                .requestMatchers("/", "/login", "/register", "/error", "/css/**", "/js/**", "/webjars/**").permitAll()
                .requestMatchers("/home").hasRole("ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/trainers/**").hasAnyRole("ADMIN", "TRAINER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );
    
        return http.build();
    }
    
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            var authorities = authentication.getAuthorities();

            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/home"); // 🔀 Admins a /home
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_TRAINER"))) {
                response.sendRedirect("/trainers"); // 🔀 Trainers a /trainers/dashboard
            } else {
                response.sendRedirect("/user/dashboard"); // 🔀 Usuarios normales a su dashboard
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 