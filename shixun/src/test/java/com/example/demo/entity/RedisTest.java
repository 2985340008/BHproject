package com.example.demo.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("name111", "张三李四王五赵六孙七");
    }

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void test2(){
//        stringRedisTemplate.opsForValue().set("key111","张三李四");
//    }

    @Test
    public void test2() {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.opsForValue().set("key222", "山东济南历下");

        String name = (String) redisTemplate.opsForValue().get("key222");
        System.out.println(name);
    }

    @Test
    public void test3() {

//        方式二：针对单个的模板对象实现序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.opsForValue().set("key333", "山东济南历城");

        String name = (String) redisTemplate.opsForValue().get("key333");
        System.out.println(name);
    }
}
