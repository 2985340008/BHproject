package com.example.mongodbdemo.dao;

import com.example.mongodbdemo.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleDao extends MongoRepository<Article,Integer> {

    /**
     * 自定义接口方法
     */
    //根据标题查询
    List<Article> findByNameLike(String name);

    //根据点击量查询
    List<Article> findByHistGreaterThan(Integer hits);
}
