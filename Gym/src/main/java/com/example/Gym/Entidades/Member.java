package com.example.Gym.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.Gym.Dto.MemberDto;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "membership_start", nullable = false)
    private LocalDate membershipStart;

    @Column(name = "membership_end")
   private LocalDate membershipEnd; // This can be null

    @Column(name = "barcode", nullable = false, unique = true, length = 50)
    private String barcode;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberDto.MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private MemberDto.PaymentMethod paymentMethod;

    @Column(name = "has_trainer")
    private boolean hasTrainer;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "trainerId")
    private Trainer trainer;

    // Getters and Setters
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public MemberDto.MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberDto.MemberStatus status) {
        this.status = status;
    }

    public MemberDto.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(MemberDto.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isHasTrainer() {
        return hasTrainer;
    }

    public void setHasTrainer(boolean hasTrainer) {
        this.hasTrainer = hasTrainer;
    }

    public Trainer getTrainer() {
        return trainer;
    }
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    
}
