package com.example.Gym.Dto;

import java.time.LocalDate;

public class PaymentDto {

    private int paymentId;
    private int memberId;
    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;

    // Constructors
    public PaymentDto() {}

    public PaymentDto(int paymentId, int memberId, LocalDate paymentDate, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
