package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Repository.UserEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
public class UserDetailsServiceImplTest {
    @InjectMocks

    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserEntryRepository userEntryRepository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
@Test
    void loadUserByUsername(){
        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((com.example.MyFirstProject.entity.User) User.builder().username("sam").password("sam").roles(String.valueOf(new ArrayList<>())).build());
       UserDetails user = userDetailsService.loadUserByUsername("sam");

    }
}
