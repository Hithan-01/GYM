package com.example.Gym.Dto;

import java.time.LocalDate;

public class MemberDto {
    private int memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate membershipStart;
    private LocalDate membershipEnd;
    private MemberStatus status;
    private String address;
    private String barcode;
    private LocalDate joinDate;
    private boolean hasTrainer;
    private PaymentMethod paymentMethod;
    private LocalDate dateOfBirth;
    private Integer trainerId;


    // Enum for Member Status
    
    public enum MemberStatus {
        ACTIVE, SUSPENDED, CANCELED
    }

    // Enum for Payment Method
    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, CASH
    }

    // Getters and Setters

     public LocalDate getDateOfBirth() {
         return dateOfBirth;
     }
     public void setDateOfBirth(LocalDate dateOfBirth) {
         this.dateOfBirth = dateOfBirth;
     }
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getMembershipStart() {
        return membershipStart;
    }

    public void setMembershipStart(LocalDate membershipStart) {
        this.membershipStart = membershipStart;
    }

    public LocalDate getMembershipEnd() {
        return membershipEnd;
    }

    public void setMembershipEnd(LocalDate membershipEnd) {
        this.membershipEnd = membershipEnd;
    }

    public MemberStatus getStatus() {
        return status;
    }
    public void setStatus(String status) {
        if (status != null) {
            this.status = MemberStatus.valueOf(status.toUpperCase());
        }
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public boolean isHasTrainer() {
        return hasTrainer;
    }

    public void setHasTrainer(boolean hasTrainer) {
        this.hasTrainer = hasTrainer;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    
    }

    public Integer  getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }
}