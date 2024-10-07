package com.example.Gym.Repositories;

import com.example.Gym.Entidades.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    // Custom query to find payments by member ID
    List<Payment> findByMemberId(int memberId);

    // Custom query to find payments within a date range
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}
