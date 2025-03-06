// package com.example.Gym.Mapping;

// import com.example.Gym.Dto.MemberDto;
// import com.example.Gym.Entidades.Payment; // Assuming you have a Payment entity
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

// @Mapper(componentModel = "spring")
// public interface PaymentMapper {

//     @Mapping(source = "paymentMethod", target = "paymentMethod")
//     Payment toEntity(MemberDto memberDto);

//     MemberDto toDto(Payment payment);

//     // Enum mapping for PaymentMethod
//     default MemberDto.PaymentMethod map(String value) {
//         if (value == null) {
//             return null;
//         }
//         return MemberDto.PaymentMethod.valueOf(value.toUpperCase());
//     }

//     default String map(MemberDto.PaymentMethod value) {
//         if (value == null) {
//             return null;
//         }
//         return value.name().toLowerCase(); // Convert to lowercase for the database
//     }
// }
