package com.example;

import com.example.pojo.User;
import com.example.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Springboot09RedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("mykey","hello world");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    void test2() throws JsonProcessingException {
        User user = new User("zhangsan",10);
//      String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    void test1() throws JsonProcessingException {
        User name1 = new User("张三", 3);
        String name = new ObjectMapper().writeValueAsString(name1);
        redisTemplate.opsForValue().set("key1", name);
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

    @Test
    void test3() throws JsonProcessingException {
        User user = new User("圆",20);
        // 使用 JSON 序列化
        String s = new ObjectMapper().writeValueAsString(user);
        // 这里直接传入一个对象
        redisTemplate.opsForValue().set("user", s);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    void test4(){
        redisUtil.set("name","张三");
        System.out.println(redisUtil.get("name"));
    }
}
