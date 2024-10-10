package com.example.Gym.Services;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Save a new member
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // Get member by ID
    public Member getMemberById(int memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // Update an existing member
    public Member updateMember(int memberId, Member memberDetails) {
        Member existingMember = getMemberById(memberId);
        existingMember.setFirstName(memberDetails.getFirstName());
        existingMember.setLastName(memberDetails.getLastName());
        existingMember.setEmail(memberDetails.getEmail());
        existingMember.setPhoneNumber(memberDetails.getPhoneNumber());
        // Update other fields as necessary

        return memberRepository.save(existingMember);
    }

    // Delete member by ID
    public void deleteMember(int memberId) {
        Member existingMember = getMemberById(memberId);
        memberRepository.delete(existingMember);
    }
}
