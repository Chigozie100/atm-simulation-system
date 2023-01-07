package com.restapi.atmsimulationsystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.atmsimulationsystem.exceptions.UserNotFoundException;
import com.restapi.atmsimulationsystem.model.User;
import com.restapi.atmsimulationsystem.payload.requests.RegisterRequest;
import com.restapi.atmsimulationsystem.payload.requests.UserAmountRequestDto;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.UserResponseDto;
import com.restapi.atmsimulationsystem.repository.UserRepository;
import com.restapi.atmsimulationsystem.security.CustomUserDetailsService;
import com.restapi.atmsimulationsystem.service.AuthenticationService;
import com.restapi.atmsimulationsystem.utils.MockUserUtils;
import com.restapi.atmsimulationsystem.utils.Responder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthenticationService service;

    @MockBean
    Responder responder;
    @MockBean
    private AuthenticationManager authenticationManager;

    private static User user = MockUserUtils.getMockUser("gozie");

    private static ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    RegisterRequest registerRequest;

    //User user;

    UserRequestDto userRequestDto;

    UserAmountRequestDto userAmountRequestDto;

    UserResponseDto userResponseDto;

    Long DUMMY_ID = 1L;


    @BeforeEach
    void setUp() throws Exception {
        userRequestDto = new UserRequestDto();
        userRequestDto.setFullName("got");
        userRequestDto.setEmail("got@gmail.com");
        userRequestDto.setAccountNumber(5672);
        userRequestDto.setPin("65");
        userRequestDto.setCardNo("752");

        userAmountRequestDto = new UserAmountRequestDto();
        userAmountRequestDto.setAmount(BigDecimal.valueOf(1000.00));

        userResponseDto = new UserResponseDto();
        userResponseDto.setAmount(BigDecimal.valueOf(6000.00));

        registerRequest = new RegisterRequest();
        registerRequest.setFullName("gozie");
        registerRequest.setEmail("gozie@gmail.com");
        registerRequest.setPin("987");

        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(userRepository);
        customUserDetailsService.loadUserByUsername(user.getEmail());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(customUserDetailsService, "secret"));
        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
    }

    @Test
    void register() throws Exception {
        RegisterRequest signUpRequest = new RegisterRequest("gozie", "gozie@gmail.com", "233");
        Mockito.doThrow(new UserNotFoundException("user not found")).when(service).register(registerRequest);
        String json = mapper.writeValueAsString(signUpRequest);
        mockMvc.perform(post("//api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.success").value("true")).andExpect(jsonPath("$.message").value("User registered successfully"));

        // Test when user provided email already exists in the database
        Mockito.doThrow(new UserNotFoundException("user not found")).when(service).register(registerRequest);
        json = mapper.writeValueAsString(signUpRequest);
        mockMvc.perform(post("//api/v1/auth/register").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.success").value("false")).andExpect(jsonPath("$.message").value("Email Address already in use!"));
    }

    @Test
    void authenticate() {
    }
}