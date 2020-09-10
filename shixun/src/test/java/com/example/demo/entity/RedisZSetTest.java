package com.example.demo.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class RedisZSetTest {

    @Autowired
    private RedisTemplate redisTemplate;
    ZSetOperations<String, String> operations = null;

    @Before
    public void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        operations = redisTemplate.opsForZSet();
    }

    //添加
    @Test
    public void testAdd() {
        operations.add("students2", "张无忌", 80);
        operations.add("students2", "张无极", 90);
        operations.add("students2", "张无机", 70);
    }

    @Test
    public void testScore() {
        //increment 功能是操作score
//        增加
        operations.incrementScore("students2", "张无忌", 60);
        //
        operations.incrementScore("students2", "张无机", -20);
    }

    @Test
    public void testFindOne() {

        //查询一个元素的分数
        Double score = operations.score("students2", "张无忌");
        System.out.println(score);

        //插叙一个元素在集合中的排名，从0开始
        Long rank = operations.rank("students2", "张无忌");
        System.out.println(rank);
    }

    //根据区间获取列表
    @Test
    public void testFindList() {

        //依据排名区间获取列表
        Set<String> students2 = operations.range("students2", 0, 2);
        for (String student : students2) {
            System.out.println(student);
        }
        Set<ZSetOperations.TypedTuple<String>> set = operations.rangeWithScores("students2", 0, 2);
        for (ZSetOperations.TypedTuple<String> tuple : set) {
            System.out.println(tuple.getValue() + "同学，分值是：" + tuple.getScore());
        }
        //依据分数区间获取列表
        Set<String> students3 = operations.rangeByScore("students2", 60, 90);
        for (String s : students3) {
            System.out.println(s);
        }
        Set<ZSetOperations.TypedTuple<String>> set1 = operations.rangeByScoreWithScores("students2", 60, 90);
        for (ZSetOperations.TypedTuple<String> tuple : set1) {
            System.out.println(tuple.getValue() + "同学，分值是：" + tuple.getScore());
        }
    }

    //统计
    @Test
    public void testCount() {

        //统计一个集合中的元素
        Long zCard = operations.zCard("students2");
        System.out.println(zCard);

        //根据分数区间统计元素数量
        Long count = operations.count("students2", 50, 100);
        System.out.println(count);
    }

    //删除
    @Test
    public void testRemove() {

        //依据key-value删除，valuekey多个
        operations.remove("students2", "张三", "张无机");

        //依据排名区间删除
        operations.removeRange("students2", 0, 1);

        //依据分数区间删除
        operations.removeRangeByScore("students2", 60, 79);
    }
}
