package com.example.MyFirstProject.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email", "rkrai059@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a =1;

    }
}
