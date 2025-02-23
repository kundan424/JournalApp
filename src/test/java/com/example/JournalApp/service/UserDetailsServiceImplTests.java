package com.example.JournalApp.service;

import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository  userRepository;

    @BeforeEach
    void  setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled("Tested")
    @Test
    public void loadUserByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(UserEntry.builder().username("fakeUser").password("testpass").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("fakeUser");
        Assertions.assertNotNull(user);
    }
}
