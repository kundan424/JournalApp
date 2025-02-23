package com.example.JournalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate  redisTemplate;

    public <T> T get(String key , Class<T> entityClass){
        try{
            Object object = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper  = new ObjectMapper();
            return  objectMapper.readValue(object.toString() , entityClass);
        }catch (Exception e){
            log.error("EXCEPTION " ,e);
            return null;
        }
    }

    public void set(String key , Object object , Long ttl){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(object);
            redisTemplate.opsForValue().set(key , jsonValue , ttl , TimeUnit.SECONDS);
        }catch (Exception e ){
            log.error("EXCEPTION" , e);
        }
    }
}
