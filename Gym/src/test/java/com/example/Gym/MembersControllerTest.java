package com.example.Gym;

import com.example.Gym.Dto.MemberDto;
import com.example.Gym.RestControllers.MembersRestController;
import com.example.Gym.Services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MembersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MembersRestController membersController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(membersController).build();
    }

    @Test
    public void testGetAllMembers() throws Exception {
        // Arrange
        MemberDto memberDto1 = new MemberDto();
        memberDto1.setFirstName("John");
        memberDto1.setLastName("Doe");

        MemberDto memberDto2 = new MemberDto();
        memberDto2.setFirstName("Jane");
        memberDto2.setLastName("Smith");

        List<MemberDto> members = Arrays.asList(memberDto1, memberDto2);

        when(memberService.findAllMembers()).thenReturn(members); // Mock service call

        // Act and Assert
        mockMvc.perform(get("/api/members")
                .contentType(MediaType.APPLICATION_JSON)) // Simulate GET request
                .andExpect(status().isOk()) // Expect a 200 OK status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON response
                .andExpect(jsonPath("$[0].firstName").value("John")) // Verify first member's first name
                .andExpect(jsonPath("$[1].firstName").value("Jane")); // Verify second member's first name

        verify(memberService, times(1)).findAllMembers(); // Verify that the service method was called once
    }
}
