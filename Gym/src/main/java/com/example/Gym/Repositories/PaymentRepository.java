package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    List<Payment> findByMember_MemberId(int memberId);


    // Custom query to find payments within a date range
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}
