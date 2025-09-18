package com.example.MyFirstProject.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public  class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entryClass){
        try {


        Object o = redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(o.toString(), entryClass);
        } catch (Exception e) {
            log.error("Error while getting data from redis", e);
        }
        return null;
    }
    public void set(String key, Object o, Long ttl, TimeUnit timeUnit){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(o);


            redisTemplate.opsForValue().set(key,json,ttl, timeUnit);

        } catch (Exception e) {
            log.error("Error while getting data from redis", e);
        }

    }



}
