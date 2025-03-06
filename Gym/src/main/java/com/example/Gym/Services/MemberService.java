package com.example.Gym.Services;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Entidades.Trainer;
import com.example.Gym.Dto.MemberDto;
import com.example.Gym.Mapping.MemberMapper;
import com.example.Gym.Repositories.MemberRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;
    
    public List<MemberDto> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        System.out.println("Members found: " + members.size());  // Check if the list is empty or contains members
        return members.stream()
                      .map(memberMapper::toDto)
                      .collect(Collectors.toList());
    }
    
    

    public MemberDto saveMember(MemberDto memberDto) {
        Member member = memberMapper.memberDtoToMember(memberDto);
        Member savedMember = memberRepository.save(member);
        return memberMapper.toDto(savedMember);
    }
    
    public MemberDto getMemberById(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found")); // Consider using a custom exception
        return memberMapper.toDto(member);
    }

    public MemberDto updateMember(int memberId, MemberDto memberDetails) {
        Member existingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found")); // Consider using a custom exception

        // Update fields
        existingMember.setFirstName(memberDetails.getFirstName());
        existingMember.setLastName(memberDetails.getLastName());
        existingMember.setEmail(memberDetails.getEmail());
        existingMember.setPhoneNumber(memberDetails.getPhoneNumber());
        existingMember.setMembershipStart(memberDetails.getMembershipStart());
        existingMember.setMembershipEnd(memberDetails.getMembershipEnd());
        existingMember.setStatus(memberDetails.getStatus());
        existingMember.setAddress(memberDetails.getAddress());
        existingMember.setBarcode(memberDetails.getBarcode());
        existingMember.setJoinDate(memberDetails.getJoinDate());
        existingMember.setHasTrainer(memberDetails.isHasTrainer());
        existingMember.setPaymentMethod(memberDetails.getPaymentMethod());

        Member updatedMember = memberRepository.save(existingMember);
        return memberMapper.toDto(updatedMember);
    }

    public void deleteMemberById(Integer memberId) {
        if (memberRepository.existsById(memberId)) {
            memberRepository.deleteById(memberId);
        } else {
            throw new NoSuchElementException("Member not found with ID: " + memberId);
        }
    }
    
    public MemberDto suspendMember(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setStatus(MemberDto.MemberStatus.SUSPENDED);
        Member updatedMember = memberRepository.save(member);
        return memberMapper.toDto(updatedMember);
    }



    public Optional<Member> getMemberByid(int memberId) {
        return memberRepository.findById(memberId);
    }


    @Transactional
    public void addMember(MemberDto memberDto, Trainer trainer) {
        Member member = new Member();
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getLastName());
        member.setEmail(memberDto.getEmail());
        member.setDateOfBirth(memberDto.getDateOfBirth());
        member.setJoinDate(memberDto.getJoinDate());
        member.setBarcode(memberDto.getBarcode());
        member.setStatus(memberDto.getStatus());
        member.setTrainer(trainer);  // ✅ Assign trainer if exists

        memberRepository.save(member);
        System.out.println("✅ DEBUG: Member added successfully!");
    }

    
    
}
