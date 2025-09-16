package com.example.MyFirstProject.service;



import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Disabled   // prevent accidental mail sending every time tests run
    @Test
    void testSendMail() {
        emailService.sendEmail(
                "rkrai059@gmail.com",  // ✅ Your receiver email
                "Testing Spring Boot Mail",
                "Hi Raka, this is a test email from Spring Boot service!"
        );
    }
}
