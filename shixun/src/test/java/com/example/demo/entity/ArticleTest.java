package com.example.demo.entity;

import com.example.demo.dao.ArticleDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ArticleTest {

    @Autowired
    private ArticleDAO articleDAO;

    @Test
    public void test1() {
        String title = "睡觉";
        String author = "张三疯";
        List<Article> list = articleDAO.findByCondition(title, author);
        for (Article a : list) {
            System.out.println(a.toString());
        }
    }

}