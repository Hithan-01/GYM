package com.example.Gym.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Gym.Entidades.Member;
import com.example.Gym.Services.MemberService;

import java.util.List;

@Controller
@RequestMapping("/admin/members")
public class MembersController {

    @Autowired
    private MemberService memberService;

    // View all members
    
    @GetMapping
    public String viewMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        System.out.println("Retrieved members: " + members); // Log members to console
        model.addAttribute("members", members);
        return "MembersTemp"; // Returns the members view
    }
    

    // Add a new member
    @GetMapping("/add")
    public String addMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member"; // Return the add member form view
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member) {
        memberService.saveMember(member);
        return "redirect:/admin/members"; // Redirects to the members list
    }

    // Edit member
    @GetMapping("/edit/{id}")
    public String editMemberForm(@PathVariable int id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "edit-member"; // Return the edit member form view
    }

    @PostMapping("/edit/{id}")
    public String updateMember(@PathVariable int id, @ModelAttribute Member memberDetails) {
        memberService.updateMember(id, memberDetails);
        return "redirect:/admin/members"; // Redirects to the members list
    }

    // Delete member
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
        return "redirect:/admin/members"; // Redirects back to the members list
    }
}
