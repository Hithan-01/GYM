package com.example.Gym.Mapping;

import com.example.Gym.Dto.MemberDto;
import com.example.Gym.Entidades.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "membershipStart", target = "membershipStart")
    @Mapping(source = "membershipEnd", target = "membershipEnd")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "barcode", target = "barcode")
    @Mapping(source = "joinDate", target = "joinDate")
    @Mapping(source = "hasTrainer", target = "hasTrainer")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth") // Add this line
    MemberDto toDto(Member member);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "membershipStart", target = "membershipStart")
    @Mapping(source = "membershipEnd", target = "membershipEnd")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "barcode", target = "barcode")
    @Mapping(source = "joinDate", target = "joinDate")
    @Mapping(source = "hasTrainer", target = "hasTrainer")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth") // Add this line
    Member memberDtoToMember(MemberDto memberDto);

    // Convert String to PaymentMethod enum
    default MemberDto.PaymentMethod mapToPaymentMethod(String value) {
        if (value == null) {
            return null;
        }
        return MemberDto.PaymentMethod.valueOf(value.toUpperCase().replace("_", "")); // Adjust to match your enum
    }

    // Convert PaymentMethod enum to String
    default String mapPaymentMethod(MemberDto.PaymentMethod value) {
        if (value == null) {
            return null;
        }
        return value.name().toLowerCase(); // Convert to lowercase for the database
    }

    // Convert String to MemberStatus enum
    default MemberDto.MemberStatus mapToMemberStatus(String value) {
        if (value == null) {
            return null;
        }
        return MemberDto.MemberStatus.valueOf(value.toUpperCase()); // Adjust to match your enum
    }

    // Convert MemberStatus enum to String
    default String mapMemberStatus(MemberDto.MemberStatus value) {
        if (value == null) {
            return null;
        }
        return value.name().toLowerCase(); // Convert to lowercase for the database
    }
}
