package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Repository.UserEntryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserEntryServiceTest {


@Autowired
private UserEntryRepository userEntryRepository;
    @Test
    public void testAdd() {


        assertNotNull(userEntryRepository.findByUserName("sam"));
    }
}
