package com.example.Gym.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Gym.Entidades.Payment;
import com.example.Gym.Repositories.PaymentRepository;
import java.time.LocalDate;

import java.util.List;

@Service
@Transactional // Use this for transaction management
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ID
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // Save a new payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Update an existing payment
    public Payment updatePayment(int id, Payment paymentDetails) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Update fields
        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setPaymentDate(paymentDetails.getPaymentDate());
        existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
        existingPayment.setTransactionId(paymentDetails.getTransactionId());

        return paymentRepository.save(existingPayment);
    }

    // Delete payment by ID
    public void deletePayment(int id) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        paymentRepository.delete(existingPayment);
    }

    // Get payments by member ID
    public List<Payment> getPaymentsByMemberId(int memberId) {
        return paymentRepository.findByMemberId(memberId); // Assuming this method is defined in your PaymentRepository
    }

    // Get payments within a specific date range
    public List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate); // Assuming this method is defined in your PaymentRepository
    }

    // Additional business logic can be added here as needed
}
